let ws;
//var stompClient = null;
const form = document.getElementById('userForm');
let uniqueIdTag;




function setConnected(connected) {
	$("#connect").prop("disabled", connected);
	$("#disconnect").prop("disabled", !connected);
	if (connected) {
		$("#conversation").show();
	}
	else {
		$("#conversation").hide();
	}
	$("#message-container-table").html("");
}


function connect() 
{   
	ws = new WebSocket('ws://localhost:8080/message');
	ws.onmessage = function(data) 
	{
		showMessage(data.data);
	}
	$("#connect").addClass('d-none')
	$("#disconnect").removeClass('d-none')
	setConnected(true);
}

function disconnect() {
	if (ws !== null) {
		ws.close();
	}
	$("#connect").removeClass('d-none')
	$("#disconnect").addClass('d-none')
	setConnected(false);
	console.log("Disconnected");
}

function sendMessage() {

	var data = $("#message-value").val()

	ws.send(data);

}


function showMessage(message) {
	$("#message-container-table").prepend(`<tr><td>${message}</td></tr>`);
}

$(function() {
	$("form").on('submit', function(e) {
		e.preventDefault();
	});

	$("#connect").click(function() {
		connect();
	});


	$("#disconnect").click(function() {
		disconnect();
	});

	$("#send-btn").click(function() {
		sendMessage();
	});

});






