
const editarBtn = document.getElementById("editarBtn");
const salvarBtn = document.getElementById("salvarBtn");
const inputs = document.querySelectorAll("input");

editarBtn.addEventListener("click", () => {
    inputs.forEach(input => input.disabled = false);
    editarBtn.style.display = "none";
    salvarBtn.style.display = "inline";
});

salvarBtn.addEventListener("click", (event) => {
    event.preventDefault(); // Evita o envio sem back-end
    alert("Dados salvos com sucesso!");
});
