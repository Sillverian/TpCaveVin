$(function(){
    getRegions();
    // Affichage de la modal
    $("#btnModal").on("click", function() {
        $('#formModifReg').css("display", "none");
        $("#formAjtReg").css('display', 'block');
        $('#staticModal').html("Ajout d'une région");
    });

    // Envoi du formulaire d'ajout de la modal
    $('#formAjtReg').on('click', ajouterRegion);

    // Envoi du formulaire de modification de la modal
    $("#formModifReg").on('click', modifierRegion);
})

// TRI
function triIDAsc(){
    $.get("region/triIdAsc", getRegionListe);
}

function triIDDesc(){
    $.get("region/triIdDesc", getRegionListe);
}

function triNomAsc(){
    $.get("region/triNomAsc", getRegionListe);
}

function triNomDesc(){
    $.get("region/triNomDesc", getRegionListe);
}

function getRegions(){
    $("#errRegion").css("display", "none");
    $("#errRegPost").css("display", "none");
    $.get("region", getRegionListe);
}

// GET
function getRegionListe(regions){
    var data ="";
    regions.forEach(function (r){
        var ligne = "<tr>";
        ligne += "<td>"+ r.id + "</td>";
        ligne += "<td id='reg"+ r.id +"'>"+ r.nom + "</td>";
        ligne += "<td> <button type='button' onclick='affichModalReg("+ r.id +")' class='btn btn-sm btn-success me-1'><i class=\"bi bi-pen-fill\">Modifier</i></button>" +
            "<button type='button' onclick='supprReg("+ r.id +")' class='btn btn-sm btn-danger'><i class=\"bi bi-trash-fill\">Supprimer</i></button></td>";

        data += ligne;
    })

    $("#bodyReg").html(data);
}

// POST
function ajouterRegion(){

    var data = {
        nom: $("#nomReg").val()
    }

    console.log(data);

    $.ajax({
        type: 'post',
        url: 'region',
        data: JSON.stringify(data),
        contentType: 'application/json;charset=utf-8',
        success: function() {
            $('#nomReg').val("");
            $('#modAjoutReg').modal('hide');
            getRegions();
        }
    })
        .fail(function(xhr) {
            $("#errRegion").css("display", "block").html("Une erreur est survenue lors de l'ajout");
            if (xhr.getResponseHeader("regDupli")){
                $("#errRegPost").css("display", "block").html(xhr.getResponseHeader("regDupli"));
            }
        })
}

// PUT

// Affichage de la modal pour modification
function affichModalReg(id){
    // Affiche la modal
    $("#modAjoutReg").modal("show");

    // Intervertit l'affichage des boutons de la modal et du titre
    $("#formAjtReg").css('display', 'none');
    $('#formModifReg').css("display", "block");
    $('#staticModal').html("Modification d'une région");

    // Instantiation des valeurs
    var value = $('#reg' + id).text();
    $('#nomReg').val(value);
    $('#idReg').val(id);
}

// Modification de la couleur
function modifierRegion() {
    var data = {
        id: $("#idReg").val(),
        nom : $("#nomReg").val()
    }

    console.log(data);

    $.ajax({
        type: 'put',
        url: 'region/' + $("#idReg").val(),
        data: JSON.stringify(data),
        contentType: "application/json;charset=utf-8",
        success: function() {
            $('#nomReg').val("");
            $('#modAjoutReg').modal('hide');
            getRegions();
        }
    })
        .fail(function(){
            getRegions();
            $("#errRegion").css("display", "block");
            $("#errRegion").html("Une erreur est survenue lors de la modification");
        })

}

// DELETE

function supprReg(id) {
    $.ajax({
        type: 'delete',
        url: 'region/' + id,
        success: function() {
            getRegions();
        }
    })
        .fail(function(){
            $("#errRegion").css("display", "block");
            $("#errRegion").html("Une erreur est survenue lors de la suppression");
        })
}