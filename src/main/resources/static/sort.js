function applySortDropdown(dropdownId, baseUrl) {
    document.addEventListener("DOMContentLoaded", function () {
        const dropdown = document.getElementById(dropdownId);
        const urlParams = new URLSearchParams(window.location.search);
        const sortParam = urlParams.get("sort");

        // Set dropdown value based on URL parameter
        if (sortParam) {
            dropdown.value = sortParam;
        }

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
