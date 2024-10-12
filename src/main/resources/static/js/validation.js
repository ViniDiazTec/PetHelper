function validateForm() {
    const name = document.getElementById("name").value;
    const email = document.getElementById("email").value;
    const address = document.getElementById("address").value;
    const questions = document.getElementById("questions").value;

    // Verifica se todos os campos estão preenchidos
    if (name === "" || email === "" || address === "" || questions === "") {
        alert("Todos os campos devem ser preenchidos!");
        return false;
    }

    // Adiciona validação de email se necessário
    const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailPattern.test(email)) {
        alert("Por favor, insira um email válido.");
        return false;
    }

    return true;
}
