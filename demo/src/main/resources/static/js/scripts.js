// JavaScript for Sidebar Toggle
document.getElementById('sidebarToggle').onclick = function() {
    const sidebar = document.getElementById('sidebar');
    const content = document.getElementById('content');
    sidebar.classList.toggle('collapsed');
    content.classList.toggle('expanded');
};

function filterPatients(status) {
    fetch(`/waiting-room/patients?status=` + status)
        .then(response => response.json())
        .then(data => {
            const tableBody = document.getElementById("patientTableBody");
            tableBody.innerHTML = ""; // Clear existing rows

            data.forEach(patient => {
                const row = `<tr>
                                            <td>${patient.id}</td>
                                            <td>${patient.cin}</td>
                                            <td>${patient.firstName}</td>
                                            <td>${patient.lastName}</td>
                                            <td>${patient.birthDate}</td>
                                            <td>${patient.credit}</td>
                                            <td>${patient.waitingRoomStatus}</td>
                                        </tr>`;
                tableBody.innerHTML += row;
            });
        })
        .catch(error => console.error("Error fetching patients:", error));
}