function lockCarport() {
    if(document.getElementById("carportShedWidth") != null || document.getElementById("carportShedLength") != null) {
        document.getElementById("updateButton").disabled = document.getElementById("carportLock").checked === true;
    }
    if(document.getElementById("carportLock").checked === true) {
        document.getElementById("updateButton").disabled = true
        document.getElementById("carportWidth").disabled = true;
        document.getElementById("carportLength").disabled = true;
        document.getElementById("carportShedWidth").disabled = true;
        document.getElementById("carportShedLength").disabled = true;
    } else {
        document.getElementById("updateButton").disabled = false
        document.getElementById("carportWidth").disabled = false;
        document.getElementById("carportLength").disabled = false;
        document.getElementById("carportShedWidth").disabled = false;
        document.getElementById("carportShedLength").disabled = false;
    }
}