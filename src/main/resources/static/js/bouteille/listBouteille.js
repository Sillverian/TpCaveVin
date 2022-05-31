$(function () {
    getBouteilles();

    //Affichage modal
    $("#btnModal").on("click", function () {
        getCouleurs();
        getRegions();
        $('#formModifBout').css("display", "none");
        $("#formAjtBout").css('display', 'block');
        $('#staticModal').html("Ajout d'une bouteille");
    });

    // Envoi du formulaire d'ajout de la modal
    $('#formAjtBout').on('click', ajouterBouteille);

    // Envoi du formulaire de modification de la modal
    $("#formModifBout").on('click', modifierBouteille);
})

function getBouteilles() {
    $("#errBouteille").css("display", "none");
    $("#errBoutPost").css("display", "none");
    $.get("http://localhost:8080/bouteille", getBouteilleListe);
}

// TRI ---------------------------------------------------------------------
function triNomAsc(){
    $.get("bouteille/triNomAsc", getBouteilleListe);
}
function triNomDesc(){
    $.get("bouteille/triNomAsc", getBouteilleListe);
}

function triPetilAsc(){
    $.get("bouteille/triPetillantAsc", getBouteilleListe);
}
function triPetilDesc(){
    $.get("bouteille/triPetillantDesc", getBouteilleListe);
}

function triMilleAsc(){
    $.get("bouteille/triMillesimeAsc", getBouteilleListe);
}
function triMilleDesc(){
    $.get("bouteille/triMillesimeDesc", getBouteilleListe);
}

function triQteAsc(){
    $.get("bouteille/triQteAsc", getBouteilleListe);
}
function triQteDesc(){
    $.get("bouteille/triQteDesc", getBouteilleListe);
}

function triCoulAsc(){
    $.get("bouteille/triCoulAsc", getBouteilleListe);
}
function triCoulDesc(){
    $.get("bouteille/triCoulDesc", getBouteilleListe);
}

function triRegAsc(){
    $.get("bouteille/triRegAsc", getBouteilleListe);
}
function triRegDesc(){
    $.get("bouteille/triRegDesc", getBouteilleListe);
}

// Recuperation des couleurs et des regions pour les selectbox
function getCouleurs(id) {
    //$("#errBouteille").css("display", "none");
    $.get("couleur", function (couleurs) {
        var data = "";
        couleurs.forEach(function (c) {
            var selected = "";
            if (id != null && id == c.id)
                selected = "selected";
            var opt = "<option value=" + c.id + " " + selected + ">" + c.nom + "</option>";

            data += opt;
        })
        $("#coulBout").html(data);
    });
}

function getRegions(id) {
    //$("#errBouteille").css("display", "none");
    $.get("region", function (regions) {
        var data = "";
        regions.forEach(function (r) {
            var selected = "";
            if (id != null && id == r.id)
                selected = "selected";
            var opt = "<option value=" + r.id + " " + selected + ">" + r.nom + "</option>";

            data += opt;
        })
        $("#regBout").html(data);
    });
}


// formatage des données
function getBouteilleListe(bouteilles) {
    var data = "";
    bouteilles.forEach(function (b) {
        var ligne = "<tr>";
        ligne += "<td id='bout" + b.id + "'>" + b.nom + "</td>";
        ligne += "<td id='mille" + b.id + "'>" + b.millesime + "</td>";
        ligne += "<td id='petil" + b.id + "'>" + (b.petillant ? 'Oui' : 'Non') + "</td>";
        ligne += "<td id='qte" + b.id + "'>" + b.quantite + "</td>";
        ligne += "<td >" + b.couleur.nom + "</td>";
        ligne += "<input type='hidden' id='valCoul" + b.id + "' value='" + b.couleur.id + "'>";
        ligne += "<td>" + b.region.nom + "</td>";
        ligne += "<input type='hidden' id='valReg" + b.id + "' value='" + b.region.id + "'>";
        ligne += "<td> <button onclick='affichModalBout(" + b.id + ")' class='btn btn-sm btn-success'><i class=\"bi bi-pen-fill\">Modifier</i></button> " +
            "<button onclick='supprBout(" + b.id + ")' class='btn btn-sm btn-danger'><i class=\"bi bi-trash-fill\">Supprimer</i></button></td>";

        data += ligne;
    })

    $("#bodyBout").html(data);
}

// POST ----------------------------------------------------------
function ajouterBouteille() {

    var check = false;

    if ($("#petilBout").is(":checked")) {
        check = true;
    }

    // console.log(check);
    // console.log($("#petilBout").is(":checked"));

    var data = {
        nom: $("#nomBout").val(),
        millesime: $("#milleBout").val(),
        petillant: check,
        quantite: $("#qteBout").val(),
        couleur: {
            id: $("#coulBout").val()
        },
        region: {
            id: $("#regBout").val()
        }
    }

    console.log(data);

    $.ajax({
        type: 'post',
        url: 'bouteille',
        data: JSON.stringify(data),
        contentType: 'application/json;charset=utf-8',
        success: function () {
            $('#nomBout').val("");
            $('#milleBout').val("");
            $('#qteBout').val("");
            $('#modAjoutBout').modal('hide');
            getBouteilles();
        }
    })
        .fail(function (xhr) {
            $("#errBouteille").css("display", "block").html("Une erreur est survenue lors de l'ajout");
            if (xhr.getResponseHeader("boutDupli")) {
                $("#errBoutPost").css("display", "block").html(xhr.getResponseHeader("boutDupli"));
            }
        })
}

// PUT ----------------------------------------------------------

// Affichage de la modal pour modification
function affichModalBout(id) {
    // Affiche la modal
    $("#modAjoutBout").modal("show");
    getCouleurs($("#valCoul" + id).val());
    getRegions($("#valReg" + id).val());

    // Intervertit l'affichage des boutons de la modal et du titre
    $("#formAjtBout").css('display', 'none');
    $('#formModifBout').css("display", "block");
    $('#staticModal').html("Modification d'une bouteille");

    // Instantiation des valeurs
    var nom = $('#bout' + id).text();
    var millesime = $('#mille' + id).text();

    var petillant = $('#petil' + id).text();

    //console.log(petillant);

    // Ajoute l'attribut checked selon la valeur de petillant
    if (petillant === "Oui") {
        $("#petilBout").attr("checked", "checked");
    } else {
        $('#petilBout').removeAttr("checked");
    }

    var quantite = $('#qte' + id).text();

    $('#nomBout').val(nom);
    $('#milleBout').val(millesime);
    $('#qteBout').val(quantite);
    $('#idBout').val(id);
}

// Modification de la bouteille
function modifierBouteille() {

    var check = false;

    if ($("#petilBout").is(":checked")) {
        check = true;
    }

    var data = {
        nom: $("#nomBout").val(),
        millesime: $("#milleBout").val(),
        petillant: check,
        quantite: $("#qteBout").val(),
        couleur: {
            id: $("#coulBout").val()
        },
        region: {
            id: $("#regBout").val()
        }
    }

    console.log(data);

    $.ajax({
        type: 'put',
        url: 'bouteille/' + $("#idBout").val(),
        data: JSON.stringify(data),
        contentType: "application/json;charset=utf-8",
        success: function () {
            $('#nomBout').val("");
            $('#milleBout').val("");
            $('#qteBout').val("");
            $('#modAjoutBout').modal('hide');
            getBouteilles();
        }
    })
        .fail(function () {
            getBouteilles();
            $("#errBouteille").css("display", "block");
            $("#errBouteille").html("Une erreur est survenue lors de la modification");
        })

}

// DELETE ----------------------------------------------------------

function supprBout(id) {
    $.ajax({
        type: 'delete',
        url: 'bouteille/' + id,
        success: function () {
            getBouteilles();
        }
    })
        .fail(function () {
            $("#errBouteille").css("display", "block");
            $("#errBouteille").html("Une erreur est survenue lors de la suppression");
        })
}