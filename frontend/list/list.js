import {clearElementChildren, createLinkCell, createButtonCell, createTextCell} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    fetchAndDisplayProcessors();
});

function fetchAndDisplayProcessors() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayProcessors(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/processors', true);
    xhttp.send();
}

function displayProcessors(processors) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    processors.processors.forEach(processor => {
        tableBody.appendChild(createTableRow(processor));
        console.log(processor);
    })

    let createDiv = document.getElementById('createLink');
    createDiv.appendChild(createLinkCell('Stwórz nowy', '../processor_add/processor_add.html'));

}

function createTableRow(processor) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(processor.name));
    tr.appendChild(createLinkCell('Pokaż', '../view/view.html?processor=' + processor.id));
    tr.appendChild(createLinkCell('Edytuj', '../processor_edit/processor_edit.html?processor=' + processor.id));
    tr.appendChild(createButtonCell('Usuń', () => deleteProcessor(processor)));
    return tr;
}

function deleteProcessor(processor) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplayProcessors();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/processors/' + processor.id, true);
    xhttp.send();
}
