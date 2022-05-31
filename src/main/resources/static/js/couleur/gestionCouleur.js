$(function(){
    getCouleurs();
    // Affichage de la modal
    $("#btnModal").on("click", function() {
        $('#formModifCouleur').css("display", "none");
        $("#formAjtCouleur").css('display', 'block');
        $('#staticModal').html("Ajout d'une couleur");
    });

    // Envoi du formulaire d'ajout de la modal
    $('#formAjtCouleur').on('click', ajouterCouleur);

    // Envoi du formulaire de modification de la modal
    //TODO creer func modifierCouleur
    $("#formModifCouleur").on('click', modifierCouleur);
})

function getCouleurs(){
    $("#errCouleur").css("display", "none");
    $("#errCoulPost").css("display", "none");
    $.get("couleur", getCouleurListe);
}

// TRI
function triIDAsc(){
    $.get("couleur/triIdAsc", getCouleurListe);
}

function triIDDesc(){
    $.get("couleur/triIdDesc", getCouleurListe);
}

function triNomAsc(){
    $.get("couleur/triNomAsc", getCouleurListe);
}

function triNomDesc(){
    $.get("couleur/triNomDesc", getCouleurListe);
}


// GET
function getCouleurListe(couleurs){
    var data ="";
    couleurs.forEach(function (c){
        var ligne = "<tr>";
        ligne += "<td>"+ c.id + "</td>";
        ligne += "<td id='coul"+ c.id +"'>"+ c.nom + "</td>";
        ligne += "<td> <button type='button' onclick='affichModalCoul("+ c.id +")' class='btn btn-sm btn-success me-1'><i class=\"bi bi-pen-fill\">Modifier</i></button>" +
            "<button type='button' onclick='supprCoul("+ c.id +")' class='btn btn-sm btn-danger'><i class=\"bi bi-trash-fill\">Supprimer</i></button></td>";

        data += ligne;
    })

    $("#bodyCoul").html(data);
}

// POST
function ajouterCouleur(){

    var data = {
        nom: $("#nomCouleur").val()
    }

    console.log(data);

    $.ajax({
        type: 'post',
        url: 'couleur',
        data: JSON.stringify(data),
        contentType: 'application/json;charset=utf-8',
        success: function() {
            $('#nomCouleur').val("");
            $('#modAjoutCoul').modal('hide');
            getCouleurs();
        }
    })
        .fail(function(xhr) {
            $("#errCouleur").css("display", "block").html("Une erreur est survenue lors de l'ajout");
            if (xhr.getResponseHeader("coulDupli")){
                $("#errCoulPost").css("display", "block").html(xhr.getResponseHeader("coulDupli"));
            }
        })
}

// PUT

// Affichage de la modal pour modification
function affichModalCoul(id){
    // Affiche la modal
    $("#modAjoutCoul").modal("show");

    // Intervertit l'affichage des boutons de la modal et du titre
    $("#formAjtCouleur").css('display', 'none');
    $('#formModifCouleur').css("display", "block");
    $('#staticModal').html("Modification d'une couleur");

    // Instantiation des valeurs
    var value = $('#coul' + id).text();
    $('#nomCouleur').val(value);
    $('#idCouleur').val(id);
}

// Modification de la couleur
function modifierCouleur() {
    var data = {
        id: $("#idCouleur").val(),
        nom : $("#nomCouleur").val()
    }

    console.log(data);

    $.ajax({
        type: 'put',
        url: 'couleur/' + $("#idCouleur").val(),
        data: JSON.stringify(data),
        contentType: "application/json;charset=utf-8",
        success: function() {
            $('#nomCouleur').val("");
            $('#modAjoutCoul').modal('hide');
            getCouleurs();
        }
    })
        .fail(function(){
            getCouleurs();
            $("#errCouleur").css("display", "block");
            $("#errCouleur").html("Une erreur est survenue lors de la modification");
        })

}

// DELETE

function supprCoul(id) {
    $.ajax({
        type: 'delete',
        url: 'couleur/' + id,
        success: function() {
            getCouleurs();
        }
    })
        .fail(function(){
            $("#errCouleur").css("display", "block");
            $("#errCouleur").html("Une erreur est survenue lors de la suppression");
        })
}