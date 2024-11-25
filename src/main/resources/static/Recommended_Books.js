// Set current index
let bookIndex = 0;

// Create timer object to cycle every 5 minutes
let timer = setInterval(myTimer, 5000);

$(document).ready(function() {
    changeBook(0);
});

// Reset Timer function not working properly to be fixed later
// function resetTimer() {
//     clearInterval(myTimer);
//     timer = setInterval(myTimer, 5000);
// }

//Show next book every time timer is called
function myTimer() {
    bookIndex++;
    showBook();
}

// Iterate to next book
function nextBook() {
    bookIndex++;
    showBook();
    // resetTimer();
}

// Iterate to previous book
function prevBook() {
    bookIndex--;
    showBook();
    // resetTimer();
}

// Change book to selected book index
function changeBook(n) {
    bookIndex = n
    showBook();
    // resetTimer();
}

// Show book index inputted
function showBook() {
    let i;
    let books = document.getElementsByClassName("book-card");
    let thumbnails = document.getElementsByClassName("thumbnail");
    let captionText = document.getElementById("caption");

    //if book to show is outside available book length, start at beginning or end
    if (bookIndex >= books.length) {bookIndex = 0}
    if (bookIndex < 0) {bookIndex = books.length-1}

    // Hide current books
    for (i = 0; i < books.length; i++) {
        books[i].style.display = "none";
    }
    // Deselect thumbnail
    for (i = 0; i < thumbnails.length; i++) {
        thumbnails[i].className = thumbnails[i].className.replace(" active", "");
    }
    // Show new book card, selected thumbnail, and new caption
    books[bookIndex].style.display = "block";
    thumbnails[bookIndex].className += " active";
    captionText.innerHTML = thumbnails[bookIndex].alt;
}