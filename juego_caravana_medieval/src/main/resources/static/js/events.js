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


document.addEventListener("DOMContentLoaded", function () {
    const startText = document.getElementById("launch_Admin");

    startText.addEventListener("click", function () {
        window.location.href = "/juego_caravana_medieval/src/main/resources/static/html/vistaAdmin.html"; 
    });

  
    
});

document.addEventListener("DOMContentLoaded", function () {
    const startText = document.getElementById("launch_ciudades");

    startText.addEventListener("click", function () {
        window.location.href = "/juego_caravana_medieval/src/main/resources/templates/ciudad-list.html"; 
    });

  
    
});

document.addEventListener("DOMContentLoaded", function () {
    const startText = document.getElementById("launch_rutas");

    startText.addEventListener("click", function () {
        window.location.href = "/juego_caravana_medieval/src/main/resources/templates/ruta-list.html"; 
    });

  
    
});



document.addEventListener("DOMContentLoaded", function () {
    const startText = document.getElementById("ruta");

    startText.addEventListener("click", function () {
        window.location.href = "/juego_caravana_medieval/src/main/resources/templates/ruta-edit.html"; 
    });

  
    
});

document.addEventListener("DOMContentLoaded", function () {
    const startText = document.getElementById("ciudad");

    startText.addEventListener("click", function () {
        window.location.href = "/juego_caravana_medieval/src/main/resources/templates/ruta-ciudad-edit.html"; 
    });

  
    
});