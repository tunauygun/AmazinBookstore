// Sort utility function
function applySortDropdown(dropdownId, baseUrl) {
    // Attach event listener to the dropdown
    document.addEventListener("DOMContentLoaded", function () {
        const dropdown = document.getElementById(dropdownId);

        // Ensure dropdown is pre-selected based on the URL query parameter
        const urlParams = new URLSearchParams(window.location.search);
        const sortParam = urlParams.get("sort");
        if (sortParam) {
            dropdown.value = sortParam;
        }

        // Event listener for sorting changes
        dropdown.addEventListener("change", function () {
            const selectedValue = dropdown.value;
            if (!selectedValue) {
                window.location.href = baseUrl; // Redirect to base URL if "Default" is selected
            } else {
                window.location.href = `${baseUrl}?sort=${selectedValue}`;
            }
        });
    });
}
