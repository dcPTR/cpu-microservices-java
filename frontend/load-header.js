document.addEventListener("DOMContentLoaded", function () {
    const headerContainer = document.getElementById("header-container");

    if (headerContainer) {
        fetch("../header.html")
            .then((response) => response.text())
            .then((html) => {
                headerContainer.innerHTML = html;
            })
            .catch((error) => {
                console.warn("Failed to load header:", error);
            });
    }
});