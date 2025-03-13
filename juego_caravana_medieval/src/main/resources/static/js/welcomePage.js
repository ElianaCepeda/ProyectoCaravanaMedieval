document.addEventListener("DOMContentLoaded", function () {
    const startText = document.getElementById("welcomeText");

    // Evento al hacer clic en "PRESIONE CUALQUIER TECLA"
    startText.addEventListener("click", function () {
        window.location.href = "/juego_caravana_medieval/src/main/resources/static/html/initPage.html"; 
    });

    // Evento al presionar cualquier tecla
    document.addEventListener("keydown", function () {
        window.location.href = "/juego_caravana_medieval/src/main/resources/static/html/initPage.html"; 
    });
});