import {
    getParameterByName,
    clearElementChildren,
    createLinkCell,
    createButtonCell,
    createTextCell,
    setTextNode
} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    fetchAndDisplayProcessor();
    fetchAndDisplayLaptops();
});

function fetchAndDisplayLaptops() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayLaptops(JSON.parse(this.responseText))
        }
    };

    let createDiv = document.getElementById('createLink');
    createDiv.appendChild(createLinkCell('Stwórz', '../laptop_add/laptop_add.html?processor=' + getParameterByName('processor')));

    console.log(getParameterByName('processor'))
    xhttp.open("GET", getBackendUrl() + '/api/processors/' + getParameterByName('processor') + '/laptops', true);
    xhttp.send();
}

function displayLaptops(laptops) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    laptops.laptops.forEach(laptop => {
        tableBody.appendChild(createTableRow(laptop));
    })
}

function createTableRow(laptop) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(laptop.model));
    tr.appendChild(createLinkCell('Edytuj', '../laptop_edit/laptop_edit.html?processor='
        + getParameterByName('processor') + '&laptop=' + laptop.id));
    tr.appendChild(createButtonCell('Usuń', () => deleteLaptop(laptop.id)));
    return tr;
}

function deleteLaptop(laptop) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplayLaptops();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/processors/' + getParameterByName('processor')
        + '/laptops/' + laptop, true);
    xhttp.send();
}


/**
 * Fetches single user and modifies the DOM tree in order to display it.
 */
function fetchAndDisplayProcessor() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayProcessor(JSON.parse(this.responseText))
        }
    };
    console.log(getParameterByName('processor'));
    xhttp.open("GET", getBackendUrl() + '/api/processors/' + getParameterByName('processor'), true);
    xhttp.send();
}

function displayProcessor(user) {
    setTextNode('name', user.name);
    setTextNode('cpuClockSpeed', user.cpuClockSpeed);
}
