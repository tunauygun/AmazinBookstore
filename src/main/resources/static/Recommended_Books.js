// Set current index
let bookIndex = 1;

// Create timer object to cycle every 5 minutes
let timer = setInterval(myTimer, 5000);

// showSlides(bookIndex);

// $(document).ready(function() {
//     showSlides(bookIndex);
// });

//Reset Timer function not working properly to be fixed later
// function resetTimer() {
//     clearInterval(myTimer);
//     timer = setInterval(myTimer, 3000);
// }

//Show next book every time timer is called
function myTimer() {
    showBook(bookIndex += 1);
}

// Iterate to next book
function nextBook() {
    showBook(bookIndex ++);
    // resetTimer();
}

// Iterate to previous book
function prevBook() {
    showBook(bookIndex --);
    // resetTimer();
}

// Change book to selected book index
function changeBook(n) {
    showBook(bookIndex = n);
    // resetTimer();
}

// Show book index inputted
function showBook(n) {
    let i;
    let books = document.getElementsByClassName("book-card");
    let thumbnails = document.getElementsByClassName("thumbnail");
    let captionText = document.getElementById("caption");
    if (n > books.length) {bookIndex = 1}
    if (n < 1) {bookIndex = books.length}
    for (i = 0; i < books.length; i++) {
        books[i].style.display = "none";
    }
    for (i = 0; i < thumbnails.length; i++) {
        thumbnails[i].className = thumbnails[i].className.replace(" active", "");
    }
    books[bookIndex-1].style.display = "block";
    thumbnails[bookIndex-1].className += " active";
    captionText.innerHTML = thumbnails[bookIndex-1].alt;
}