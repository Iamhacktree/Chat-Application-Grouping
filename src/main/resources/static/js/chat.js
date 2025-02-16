/**
 * 
 */
let stompClient = null;
let currentGroup = null;

/**
 * Fetch available groups from the backend and populate the dropdown.
 */
async function loadGroups() {
    try {
        const response = await fetch("/api/groups");  // Backend should return a list of group names
        const groups = await response.json();
        
        const groupSelect = document.getElementById("groupSelect");
        groupSelect.innerHTML = '<option value="">Select a Group</option>'; // Reset dropdown

        groups.forEach(group => {
            const option = document.createElement("option");
            option.value = group;
            option.textContent = group;
            groupSelect.appendChild(option);
        });
    } catch (error) {
        console.error("Error loading groups:", error);
    }
}

/**
 * Connect to the selected group via WebSocket.
 */
function connectToGroup() {
    const groupName = document.getElementById("groupSelect").value;

    if (!groupName) {
        alert("Please select a group to join!");
        return;
    }

    if (stompClient !== null) {
        stompClient.unsubscribe('/group/' + currentGroup);
    }

    const socket = new SockJS('/chat');
    stompClient = Stomp.over(socket);

    stompClient.connect({}, function (frame) {
        console.log("Connected: ", frame);
        currentGroup = groupName;
		setConnected(true);
		
        // Subscribe to the selected group
        stompClient.subscribe('/group/' + currentGroup, function (message) {
            showMessage(JSON.parse(message.body));
        });

        
    }, function (error) {
        console.error("WebSocket connection error: ", error);
        alert("Failed to connect to WebSocket server.");
        setConnected(false);
    });
}


/**
 * Enable or disable the send button based on connection status
 * @param {boolean} connected - WebSocket connection status
 */
function setConnected(connected) {
    document.getElementById('sendMessage').disabled = !connected;
}


function showMessage(message) {
    const chat = document.getElementById('chat');
    const messageElement = document.createElement('div');
    
    messageElement.textContent = `${message.sender}: ${message.content}`;
    messageElement.class = "border-bottom mb-1";
    
    chat.appendChild(messageElement);
    chat.scrollTop = chat.scrollHeight; // Auto-scroll to latest message
}


/**
 * Send user message to WebSocket server.
 */
function sendMessage() {
    const sender = document.getElementById("senderInput").value.trim();
    const content = document.getElementById("messageInput").value.trim();
    
    if (!sender || !content || !currentGroup) {
        alert("Please enter a name, group, and message!");
        return;
    }

    const chatMessage = { sender, content };
    
    // Send message to the specific group
    stompClient.send("/app/sendMessage/" + currentGroup, {}, JSON.stringify(chatMessage));
    
    document.getElementById("messageInput").value = ''; // Clear input field
}

/**
 * Create a new chat group and refresh the dropdown.
 */
async function createGroup() {
    const newGroupName = document.getElementById("newGroupInput").value.trim();

    if (!newGroupName) {
        alert("Please enter a group name!");
        return;
    }

    try {
        await fetch("/api/groups", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ name: newGroupName })
        });

        alert("Group created successfully!");
        document.getElementById("newGroupInput").value = ""; // Clear input field
        loadGroups(); // Refresh the dropdown with the new group
    } catch (error) {
        console.error("Error creating group:", error);
    }
}

// Load groups on page load
window.onload = loadGroups;
document.getElementById("sendMessage").addEventListener("click", sendMessage);
