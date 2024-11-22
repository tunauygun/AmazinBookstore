$(document).ready(function () {
    // Load the books initially
    loadBooks();

    // Handle Add Book form submission
    $('#addBookForm').on('submit', function (event) {
        event.preventDefault();
        addBook();
    });

    // Use event delegation for edit and delete buttons
    $('#booksTable').on('click', '.edit-button', function () {
        var bookId = $(this).data('id');
        editBook(bookId);
    });

    $('#booksTable').on('click', '.delete-button', function () {
        var bookId = $(this).data('id');
        deleteBook(bookId);
    });

    // Apply sorting using the external sort.js utility
    applySortDropdown('sortDropdown', loadBooks, '/amazinBookstore/admin/books');

});

function loadBooks(sortOrder = '') {
    let url = '/api/books';
    if (sortOrder) {
        url += `?sort=${sortOrder}`;
    }

    $.ajax({
        url: url,
        type: 'GET',
        success: function (books) {
            var tbody = $('#booksTable tbody');
            tbody.empty();
            if (books.length > 0) {
                books.forEach(function (book) {
                    var authors = book.authorNames ? book.authorNames.join(", ") : "No Authors";
                    var row = `
                        <tr>
                            <td>${book.title}</td>
                            <td>${authors}</td>
                            <td>${book.genre}</td>
                            <td>${book.isbn}</td>
                            <td>${book.pageCount}</td>
                            <td>${book.price}</td>
                            <td>${book.publisherName}</td>
                            <td>${book.quantity}</td>
                            <td>${book.publicationYear}</td>
                            <td>
                                <button class="edit-button" data-id="${book.id}">Edit</button>
                                <button class="delete-button" data-id="${book.id}">Delete</button>
                            </td>
                        </tr>`;
                    tbody.append(row);
                });
            } else {
                tbody.append('<tr><td colspan="10">No books found.</td></tr>');
            }
        },
        error: function (xhr, status, error) {
            alert('Failed to load books: ' + xhr.responseText);
        }
    });
}


// Function to add a new book
function addBook() {
    var title = $('#title').val();
    var authorNames = $('#authorNames').val().split(",").map(item => item.trim());
    var genre = $('#genre').val();
    var isbn = $('#isbn').val();
    var pageCount = $('#pageCount').val();
    var price = $('#price').val();
    var publisherName = $('#publisherName').val();
    var quantity = $('#quantity').val();
    var url = $('#url').val();
    var publicationYear = $('#year').val();

    $.ajax({
        url: '/api/books',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({
            title: title,
            authorNames: authorNames,
            genre: genre,
            isbn: isbn,
            pageCount: pageCount,
            price: price,
            publisherName: publisherName,
            quantity: quantity,
            url: url,
            publicationYear: publicationYear
        }),
        success: function() {
            alert('Book added successfully!');
            $('#addBookForm')[0].reset();
            loadBooks(); // Refresh the table
        },
        error: function(xhr, status, error) {
            alert('Failed to add book: ' + xhr.responseText);
        }
    });
}

// Function to delete a book
function deleteBook(id) {
    if (confirm('Are you sure you want to delete this book?')) {
        $.ajax({
            url: '/api/books/' + id,
            type: 'DELETE',
            success: function() {
                alert('Book deleted successfully!');
                loadBooks(); // Refresh the table
            },
            error: function(xhr, status, error) {
                alert('Failed to delete book: ' + xhr.responseText);
            }
        });
    }
}

// Function to edit a book
function editBook(id) {
    $.ajax({
        url: '/api/books/' + id,
        type: 'GET',
        success: function(book) {
            var newTitle = prompt("Edit title:", book.title);
            var newAuthorNames = prompt("Edit author names (comma separated):", book.authorNames.join(", "));
            var newGenre = prompt("Edit genre:", book.genre);
            var newIsbn = prompt("Edit ISBN:", book.isbn);
            var newPageCount = prompt("Edit page count:", book.pageCount);
            var newPrice = prompt("Edit price:", book.price);
            var newPublisherName = prompt("Edit publisher name:", book.publisherName);
            var newQuantity = prompt("Edit quantity:", book.quantity);
            var newUrl = prompt("Edit URL:", book.url);
            var newPublicationYear = prompt("Edit year:", book.publicationYear);

            if (newTitle && newAuthorNames && newGenre && newIsbn && newPageCount && newPrice && newPublisherName && newQuantity && newUrl && newPublicationYear) {
                $.ajax({
                    url: '/api/books/' + id,
                    type: 'PUT',
                    contentType: 'application/json',
                    data: JSON.stringify({
                        title: newTitle,
                        authorNames: newAuthorNames.split(",").map(item => item.trim()),
                        genre: newGenre,
                        isbn: newIsbn,
                        pageCount: parseInt(newPageCount),
                        price: parseFloat(newPrice),
                        publisherName: newPublisherName,
                        quantity: parseInt(newQuantity),
                        url: newUrl,
                        publicationYear: parseInt(newPublicationYear)
                    }),
                    success: function() {
                        alert('Book updated successfully!');
                        loadBooks(); // Refresh the table
                    },
                    error: function(xhr, status, error) {
                        alert('Failed to update book: ' + xhr.responseText);
                    }
                });
            }
        },
        error: function(xhr, status, error) {
            alert('Failed to fetch book details: ' + xhr.responseText);
        }
    });
}
