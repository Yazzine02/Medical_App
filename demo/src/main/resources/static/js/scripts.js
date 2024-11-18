// JavaScript for Sidebar Toggle
document.getElementById('sidebarToggle').onclick = function() {
    const sidebar = document.getElementById('sidebar');
    const content = document.getElementById('content');
    sidebar.classList.toggle('collapsed');
    content.classList.toggle('expanded');
};

// Add new patient to waiting room
function submitPatientForm() {
    const formData = {
        patientId: document.getElementById('patientId').value,
        firstName: document.getElementById('firstName').value,
        lastName: document.getElementById('lastName').value,
        // Add more fields as needed
    };

    fetch('/waiting-room/add-patient', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(formData)
    })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                // Update the table or reload page to show new patient in waiting room
                $('#addPatientModal').modal('hide');
                location.reload();
            } else {
                alert('Failed to add patient to waiting room');
            }
        })
        .catch(error => console.error("Error:", error));
}

function validateCIN() {
    const cin = document.getElementById("cin").value;
    const regex = /^[a-zA-Z0-9]{7}$/;
    const errorMessage = document.getElementById("error-message");

    if (!regex.test(cin)) {
        errorMessage.style.display = "inline";
        return false;
    }
    errorMessage.style.display = "none";
    return true;
}