$(function(){

    console.log('insertion booststrap chargé');
    $("#jquery").append().load("./paramPage.html");

    const tooltipTriggerList = document.querySelectorAll('[data-bs-toggle="tooltip"]')
    const tooltipList = [...tooltipTriggerList].map(tooltipTriggerEl => new bootstrap.Tooltip(tooltipTriggerEl))
});