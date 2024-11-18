// JavaScript for Sidebar Toggle
document.getElementById('sidebarToggle').onclick = function() {
    const sidebar = document.getElementById('sidebar');
    const content = document.getElementById('content');
    sidebar.classList.toggle('collapsed');
    content.classList.toggle('expanded');
};
// Waiting Room functions
function getPatients(status) {
    fetch(`/waiting-room/patients?status=` + status)
        .then(response => response.json())
        .then(data => {
            const tableBody = document.getElementById("patientTableBody");
            tableBody.innerHTML = ""; // Clear existing rows

            data.forEach(patient => {
                const row = `
                    <tr>
                        <td>${patient.id}</td>
                        <td>${patient.cin}</td>
                        <td>${patient.firstName}</td>
                        <td>${patient.lastName}</td>
                        <td>${patient.birthDate}</td>
                        <td>${patient.credit}</td>
                        <td>${patient.waitingRoomStatus}</td>
                        <td>
                            <!-- Select Button -->
                            <a href="/patient-profile/${patient.id}" class="btn btn-info">Select</a>
                            <!-- Cancel Button -->
                            <form action="/waiting-room/cancel/${patient.id}" method="post" style="display:inline;" onsubmit="return confirm('Are you sure you want to cancel this patient?');">
                                <button type="submit" class="btn btn-danger">Cancel</button>
                            </form>
                        </td>
                    </tr>`;
                tableBody.innerHTML += row;
            });
        })
        .catch(error => console.error("Error fetching patients:", error));
}

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