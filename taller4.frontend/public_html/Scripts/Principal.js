    const MESES = [
    "Enero","Febrero","Marzo","Abril","Mayo","Junio",
    "Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"
];

// Datos fijos de prueba (sin conexión al backend)
const actividades = [
    { id: 1, titulo: "Limpiar la sala",     fechaTerminacion: "2026-05-03" },
    { id: 2, titulo: "Lavar los platos",    fechaTerminacion: "2026-05-05" },
    { id: 3, titulo: "Tender la cama",      fechaTerminacion: "2026-05-15" },
    { id: 4, titulo: "Sacar la basura",     fechaTerminacion: "2026-05-12" },
    { id: 5, titulo: "Preparar el desayuno",fechaTerminacion: "2026-05-20" }
];

let mesActual = new Date().getMonth();
let anioActual = new Date().getFullYear();

function generarCalendario(anio, mes) {
    document.getElementById("mes-anio-titulo").textContent = `${MESES[mes]} ${anio}`;

    const contenedor = document.getElementById("dias-calendario");
    contenedor.innerHTML = "";

    const primerDia = new Date(anio, mes, 1).getDay();
    const totalDias = new Date(anio, mes + 1, 0).getDate();

    for (let i = 0; i < primerDia; i++) {
        const vacia = document.createElement("div");
        vacia.classList.add("dia-celda");
        vacia.style.cssText = "background:transparent; border:none;";
        contenedor.appendChild(vacia);
    }

    for (let dia = 1; dia <= totalDias; dia++) {
        const celda = document.createElement("div");
        celda.classList.add("dia-celda");

        const numero = document.createElement("span");
        numero.classList.add("dia-numero");
        numero.textContent = dia;
        celda.appendChild(numero);

        const mes2 = String(mes + 1).padStart(2, "0");
        const dia2 = String(dia).padStart(2, "0");
        const fechaDia = `${anio}-${mes2}-${dia2}`;

        const actividadesDelDia = actividades.filter(
            a => a.fechaTerminacion === fechaDia
        );

        actividadesDelDia.forEach(actividad => {
            const chip = document.createElement("button");
               sessionStorage.setItem("idActividad", actividad.id);
             chip.classList.add("actividad-btn");
            chip.textContent = actividad.titulo;

            chip.addEventListener("click", function() {
                sessionStorage.setItem("idActividad", actividad.id);
                window.location.href = "Actividad.html";
            });

            celda.appendChild(chip);
        });

        contenedor.appendChild(celda);
    }
}

document.getElementById("mes-anterior").addEventListener("click", function() {
    mesActual--;
    if (mesActual < 0) { mesActual = 11; anioActual--; }
    generarCalendario(anioActual, mesActual);
});

document.getElementById("mes-siguiente").addEventListener("click", function() {
    mesActual++;
    if (mesActual > 11) { mesActual = 0; anioActual++; }
    generarCalendario(anioActual, mesActual);
});

document.getElementById("añadir").addEventListener("click", function() {
    window.location.href = "AñadirActividad.html";
});

document.getElementById("Consultar").addEventListener("click", function() {
    window.location.href = "Principal.html";
});

document.getElementById("ajustes").addEventListener("click", function() {
    window.location.href = "Ajustes.html";
});

document.addEventListener("DOMContentLoaded", function() {
    generarCalendario(anioActual, mesActual);
});