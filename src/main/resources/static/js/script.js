function handleButtonClick() {
  // Your code to perform actions when the button is clicked goes here.

  // Show the success message
  const messageShow = document.getElementById('messageShow');
  //messageShow.style.display = 'block';

  // Reset the message after 2 seconds (you can adjust the time as needed)
  setTimeout(() => {
    messageShow.style.display = 'none';
  }, 2000);
}

 // JavaScript to handle dropdown toggle
  $(document).ready(function() {
    // Show the dropdown list when the button is clicked or hovered over
    $('#dropdownButton1').on('click mouseenter', function() {
      $('#dropdownList1').show();
    });
    
    // Hide the dropdown list when the mouse leaves the list
    $('#dropdownList1').mouseleave(function() {
      $(this).hide();
    });
  });

 // JavaScript to handle dropdown toggle
  $(document).ready(function() {
    // Show the dropdown list when the button is clicked or hovered over
    $('#dropdownButton2').on('click mouseenter', function() {
      $('#dropdownList2').show();
    });
    
    // Hide the dropdown list when the mouse leaves the list
    $('#dropdownList2').mouseleave(function() {
      $(this).hide();
    });
  });

// JavaScript to handle dropdown toggle
  $(document).ready(function() {
    // Show the dropdown list when the button is clicked or hovered over
    $('#dropdownButton3').on('click mouseenter', function() {
      $('#dropdownList3').show();
    });
    
    // Hide the dropdown list when the mouse leaves the list
    $('#dropdownList3').mouseleave(function() {
      $(this).hide();
    });
  });

function startCharging() {
	// Get the input element
	const inputElement = document.getElementById("message-value");
	const connector0 = document.getElementById("connector0");
	const connector1 = document.getElementById("connector1");
	const connector2 = document.getElementById("connector2");
	
	 let selectedConnector = null;

  if (connector0.checked) {
    selectedConnector = connector0.value;
  } else if (connector1.checked) {
    selectedConnector = connector1.value;
  } else if (connector2.checked) {
    selectedConnector = connector2.value;
  }

  if (selectedConnector !== null) {
    //alert(`Selected Connector: ${selectedConnector}`);
  } else {
    alert("No connector selected.");
  }
	// Prompt the user for input and store it in a variable
	const userInput = [2, "jgdkjgshkjhkjdshkhak", "RemoteStartTransaction", { "connectorId": `${selectedConnector}`, "idTag": "powerlogix", "chargingProfile": null }];

	// Convert the object to a JSON string
	const jsonString = JSON.stringify(userInput);

	// Update the input element's value with the user input
	inputElement.value = jsonString;
}


function stopCharging() {
	// Get the input element
	const inputElement = document.getElementById("message-value");

	// Prompt the user for input and store it in a variable
	const userInput = [2,"15980d2c-7b01-4fe3-8d2e-e31dd8f1d356","RemoteStopTransaction",{"transactionId":12345}];


	// Convert the object to a JSON string
	const jsonString = JSON.stringify(userInput);

	// Update the input element's value with the user input
	inputElement.value = jsonString;
}

function getConfiguration() {
	// Get the input element
	const inputElement = document.getElementById("message-value");

	// Prompt the user for input and store it in a variable
	const userInput = [2,"xyzhdsvdhvbdvf","GetConfiguration",{}];


	// Convert the object to a JSON string
	const jsonString = JSON.stringify(userInput);

	// Update the input element's value with the user input
	inputElement.value = jsonString;
}

function changeConfiguration() {
	// Get the input element
	const inputElement = document.getElementById("message-value");

	// Prompt the user for input and store it in a variable
	const userInput = [2,"4616c787-9dd1-4bb7-a8bb-9780deb24871","ChangeConfiguration",{"key":"MeterValueSampleInterval","value":"20"}];


	// Convert the object to a JSON string
	const jsonString = JSON.stringify(userInput);

	// Update the input element's value with the user input
	inputElement.value = jsonString;
}

//Trigger Message Part
function statusNotification() {
	// Get the input element
	const inputElement = document.getElementById("message-value");
	const connector0 = document.getElementById("connector0");
	const connector1 = document.getElementById("connector1");
	const connector2 = document.getElementById("connector2");
	
	 let selectedConnector = null;

  if (connector0.checked) {
    selectedConnector = connector0.value;
  } else if (connector1.checked) {
    selectedConnector = connector1.value;
  } else if (connector2.checked) {
    selectedConnector = connector2.value;
  }

  if (selectedConnector !== null) {
    //alert(`Selected Connector: ${selectedConnector}`);
  } else {
    alert("No connector selected.");
  }
	// Prompt the user for input and store it in a variable
	const userInput = [2,"xyzhdsvdhvbdvf","TriggerMessage",{"requestedMessage":"StatusNotification","connectorId":`${selectedConnector}`}];

	// Convert the object to a JSON string
	const jsonString = JSON.stringify(userInput);

	// Update the input element's value with the user input
	inputElement.value = jsonString;
}

function bootNotification() {
	// Get the input element
	const inputElement = document.getElementById("message-value");
	const connector0 = document.getElementById("connector0");
	const connector1 = document.getElementById("connector1");
	const connector2 = document.getElementById("connector2");
	
	 let selectedConnector = null;

  if (connector0.checked) {
    selectedConnector = connector0.value;
  } else if (connector1.checked) {
    selectedConnector = connector1.value;
  } else if (connector2.checked) {
    selectedConnector = connector2.value;
  }

  if (selectedConnector !== null) {
    //alert(`Selected Connector: ${selectedConnector}`);
  } else {
    alert("No connector selected.");
  }
	// Prompt the user for input and store it in a variable
	const userInput = [2,"xyzhdsvdhvbdvf","TriggerMessage",{"requestedMessage":"BootNotification","connectorId":`${selectedConnector}`}];

	// Convert the object to a JSON string
	const jsonString = JSON.stringify(userInput);

	// Update the input element's value with the user input
	inputElement.value = jsonString;
}

function meterValues() {
	// Get the input element
	const inputElement = document.getElementById("message-value");
	const connector0 = document.getElementById("connector0");
	const connector1 = document.getElementById("connector1");
	const connector2 = document.getElementById("connector2");
	
	 let selectedConnector = null;

  if (connector0.checked) {
    selectedConnector = connector0.value;
  } else if (connector1.checked) {
    selectedConnector = connector1.value;
  } else if (connector2.checked) {
    selectedConnector = connector2.value;
  }

  if (selectedConnector !== null) {
    //alert(`Selected Connector: ${selectedConnector}`);
  } else {
    alert("No connector selected.");
  }
	// Prompt the user for input and store it in a variable
	const userInput = [2,"xyzhdsvdhvbdvf","TriggerMessage",{"requestedMessage":"MeterValues","connectorId":`${selectedConnector}`}];

	// Convert the object to a JSON string
	const jsonString = JSON.stringify(userInput);

	// Update the input element's value with the user input
	inputElement.value = jsonString;
}

function heartBeat() {
	// Get the input element
	const inputElement = document.getElementById("message-value");
	const connector0 = document.getElementById("connector0");
	const connector1 = document.getElementById("connector1");
	const connector2 = document.getElementById("connector2");
	
	 let selectedConnector = null;

  if (connector0.checked) {
    selectedConnector = connector0.value;
  } else if (connector1.checked) {
    selectedConnector = connector1.value;
  } else if (connector2.checked) {
    selectedConnector = connector2.value;
  }

  if (selectedConnector !== null) {
    //alert(`Selected Connector: ${selectedConnector}`);
  } else {
    alert("No connector selected.");
  }
	// Prompt the user for input and store it in a variable
	const userInput = [2,"xyzhdsvdhvbdvf","TriggerMessage",{"requestedMessage":"Heartbeat","connectorId":`${selectedConnector}`}];

	// Convert the object to a JSON string
	const jsonString = JSON.stringify(userInput);

	// Update the input element's value with the user input
	inputElement.value = jsonString;
}


function updateFirmWare() {
	// Get the input element
	const inputElement = document.getElementById("message-value");

	// Prompt the user for input and store it in a variable
	const userInput = [2,"xyzhdsvdhvbdvf","UpdateFirmware",{"location":"Lucknow","retries":2,"retrieveDate":"","retryInterval":30}];

	// Convert the object to a JSON string
	const jsonString = JSON.stringify(userInput);

	// Update the input element's value with the user input
	inputElement.value = jsonString;
}

function reset() {
	// Get the input element
	const inputElement = document.getElementById("message-value");

	// Prompt the user for input and store it in a variable
	const userInput = [2,"9c8fa389-82b9-43bc-81ab-88bfb36d0ba2","Reset",{"type": " Soft"}]; 


	// Convert the object to a JSON string
	const jsonString = JSON.stringify(userInput);

	// Update the input element's value with the user input
	inputElement.value = jsonString;
}

function changeAvailability() {
	// Get the input element
	const inputElement = document.getElementById("message-value");

	// Prompt the user for input and store it in a variable


	// Convert the object to a JSON string
	const jsonString = JSON.stringify(userInput);

	// Update the input element's value with the user input
	inputElement.value = jsonString;
}

function Operative() {
	// Get the input element
	const inputElement = document.getElementById("message-value");
	const connector0 = document.getElementById("connector0");
	const connector1 = document.getElementById("connector1");
	const connector2 = document.getElementById("connector2");
	
	 let selectedConnector = null;

  if (connector0.checked) {
    selectedConnector = connector0.value;
  } else if (connector1.checked) {
    selectedConnector = connector1.value;
  } else if (connector2.checked) {
    selectedConnector = connector2.value;
  }

  if (selectedConnector !== null) {
    //alert(`Selected Connector: ${selectedConnector}`);
  } else {
    alert("No connector selected.");
  }
	// Prompt the user for input and store it in a variable
	const userInput = [2,"9c8fa389-82b9-43bc-81ab-88bfb36d0ba1","ChangeAvailability",{"connectorId": `${selectedConnector}`,"type":"Operative"}]; 

	// Convert the object to a JSON string
	const jsonString = JSON.stringify(userInput);

	// Update the input element's value with the user input
	inputElement.value = jsonString;
}

function Inoperative() {
	// Get the input element
	const inputElement = document.getElementById("message-value");
	const connector0 = document.getElementById("connector0");
	const connector1 = document.getElementById("connector1");
	const connector2 = document.getElementById("connector2");
	
	 let selectedConnector = null;

  if (connector0.checked) {
    selectedConnector = connector0.value;
  } else if (connector1.checked) {
    selectedConnector = connector1.value;
  } else if (connector2.checked) {
    selectedConnector = connector2.value;
  }

  if (selectedConnector !== null) {
    //alert(`Selected Connector: ${selectedConnector}`);
  } else {
    alert("No connector selected.");
  }
	// Prompt the user for input and store it in a variable
	const userInput = [2,"9c8fa389-82b9-43bc-81ab-88bfb36d0ba1","ChangeAvailability",{"connectorId": `${selectedConnector}`,"type":"Inoperative"}]; 

	// Convert the object to a JSON string
	const jsonString = JSON.stringify(userInput);

	// Update the input element's value with the user input
	inputElement.value = jsonString;
}

function unLockConnector() {
	// Get the input element
	const inputElement = document.getElementById("message-value");
	const connector0 = document.getElementById("connector0");
	const connector1 = document.getElementById("connector1");
	const connector2 = document.getElementById("connector2");
	
	 let selectedConnector = null;

  if (connector0.checked) {
    selectedConnector = connector0.value;
  } else if (connector1.checked) {
    selectedConnector = connector1.value;
  } else if (connector2.checked) {
    selectedConnector = connector2.value;
  }

  if (selectedConnector !== null) {
    //alert(`Selected Connector: ${selectedConnector}`);
  } else {
    alert("No connector selected.");
  }
	// Prompt the user for input and store it in a variable
	const userInput = [2, "12345", "UnlockConnector",{"connectorId": `${selectedConnector}`}];  

	// Convert the object to a JSON string
	const jsonString = JSON.stringify(userInput);

	// Update the input element's value with the user input
	inputElement.value = jsonString;
}

function getDiagnostics() {
	// Get the input element
	const inputElement = document.getElementById("message-value");

	// Prompt the user for input and store it in a variable
	const userInput = [2,"dfbc13ec-6bf3-4706-80d8-36d28eeb2c02","GetDiagnostics",{"location":"ftp://ftpuser:ftpuser77@97.74.88.72:21/ftp/upload/VEVC-63-01","retries":5,"retryInterval":5,"startTime":"2023-07-15T00:01:01.000Z","stopTime":"2023-07-15T10:29:25.722Z"}];   


	// Convert the object to a JSON string
	const jsonString = JSON.stringify(userInput);

	// Update the input element's value with the user input
	inputElement.value = jsonString;
}

function sendLocalList() {
	// Get the input element
	const inputElement = document.getElementById("message-value");

	// Prompt the user for input and store it in a variable
	const userInput = [2, "f5598b01-515b-47e7-9de2-3085070310a1", "SendLocalList", {
    "listVersion": 1,
    "localAuthorizationList": [{
        "idTag": "4C9FF75B",
        "idTagInfo": {
            "status": "Accepted"
        }
    }, {
        "idTag": "349EDA29",
        "idTagInfo": {
            "status": "Accepted"
        }
    }, {
        "idTag": "347125",
        "idTagInfo": {
            "status": "Accepted"
        }
    }],
    "updateType": "Full"
}];   


	// Convert the object to a JSON string
	const jsonString = JSON.stringify(userInput);

	// Update the input element's value with the user input
	inputElement.value = jsonString;
}

function getLocalListVersion() {
	// Get the input element
	const inputElement = document.getElementById("message-value");

	// Prompt the user for input and store it in a variable
	const userInput = [2,"a384cb0c-4f4e-401f-a2cf-c1005145e92b","GetLocalListVersion",{}]; 

	// Convert the object to a JSON string
	const jsonString = JSON.stringify(userInput);

	// Update the input element's value with the user input
	inputElement.value = jsonString;
}
