/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var arquivoInput = document.getElementById("arquivoInput");
var enviar = document.getElementById("enviar");
enviar.addEventListener("click", function (event) {
  if (arquivoInput.files.length === 0) {
    alert("Nenhum Arquivo Selecionado");
    return;
  }

  if (arquivoInput.files[0].type.indexOf("pdf") !== 0) {
    alert("Este arquivo não é um PDF");
    $(".invalid-feedback").addClass("active");
    return;
  }
});
(function () {
    'use strict';
    window.addEventListener('load', function () {
        var form = document.getElementById('needs-validation');
        form.addEventListener('submit', function (event) {
            if ((form.checkValidity() === false)) {
                event.preventDefault();
                event.stopPropagation();
            }
            form.classList.add('was-validated');
        }, false);
    }, false);
})();
