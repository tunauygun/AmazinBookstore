$(document).ready(function() {
    // Load the users initially
    loadUsers();

    // Handle Add User form submission
    $('#addUserForm').on('submit', function (event) {
        event.preventDefault();
        addUser();
    });

    // Use event delegation for edit and delete buttons
    $('#usersTable').on('click', '.edit-button', function() {
        var userId = $(this).data('id');
        editUser(userId);
    });

    $('#usersTable').on('click', '.delete-button', function() {
        var userId = $(this).data('id');
        deleteUser(userId);
    });
});

// Function to load all users and populate the table
function loadUsers() {
    $.ajax({
        url: '/api/users',
        type: 'GET',
        success: function(users) {
            var tbody = $('#usersTable tbody');
            tbody.empty();
            if (users.length > 0) {
                users.forEach(function(user) {
                    var row = `
                        <tr>
                            <td>${user.name}</td>
                            <td>${user.isAdmin}</td>
                            <td>
                                <button class="edit-button" data-id="${user.id}">Edit</button>
                                <button class="delete-button" data-id="${user.id}">Delete</button>
                            </td>
                        </tr>`;
                    tbody.append(row);
                });
            } else {
                tbody.append('<tr><td colspan="10">No users found.</td></tr>');
            }
        },
        error: function(xhr, status, error) {
            alert('Failed to load users: ' + xhr.responseText);
        }
    });
}

// Function to add a new user
function addUser() {
    var name = $('#name').val();
    var adminStatus = $('#adminStatus').is(':checked');

    $.ajax({
        url: '/api/users',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({
            name: name,
            isAdmin: adminStatus,
        }),
        success: function() {
            alert('User added successfully!');
            $('#addUserForm')[0].reset();
            loadUsers(); // Refresh the table
        },
        error: function(xhr, status, error) {
            alert('Failed to add user: ' + xhr.responseText);
        }
    });
}

// Function to delete a user
function deleteUser(id) {
    if (confirm('Are you sure you want to delete this user?')) {
        $.ajax({
            url: '/api/users/' + id,
            type: 'DELETE',
            success: function() {
                alert('User deleted successfully!');
                loadUsers(); // Refresh the table
            },
            error: function(xhr, status, error) {
                alert('Failed to delete user: ' + xhr.responseText);
            }
        });
    }
}

// Function to edit a user
function editUser(id) {
    $.ajax({
        url: '/api/users/' + id,
        type: 'GET',
        success: function(user) {
            var newName = prompt("Edit name:", user.name);
            var newAdminStatus = prompt("Edit admin status:", user.adminStatus);


            if (newName && newAdminStatus) {
                $.ajax({
                    url: '/api/users/' + id,
                    type: 'PUT',
                    contentType: 'application/json',
                    data: JSON.stringify({
                        name: newName,
                        isAdmin: newAdminStatus,

                    }),
                    success: function() {
                        alert('User updated successfully!');
                        loadUsers(); // Refresh the table
                    },
                    error: function(xhr, status, error) {
                        alert('Failed to update user: ' + xhr.responseText);
                    }
                });
            }
        },
        error: function(xhr, status, error) {
            alert('Failed to fetch user details: ' + xhr.responseText);
        }
    });
}
