import {getParameterByName} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');

    infoForm.addEventListener('submit', event => addInfoAction(event));

    fetchAndDisplayProcessor();
});

function fetchAndDisplayProcessor() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let response = JSON.parse(this.responseText);
            for (const [key, value] of Object.entries(response)) {
                console.log(key);
                console.log(value);
                let input = document.getElementById(key);
                if (input) {
                    input.value = value;
                }
            }
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/processors/' + getParameterByName('processor'), true);
    xhttp.send();
}

function addInfoAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            fetchAndDisplayProcessor();
        }
    };
    xhttp.open("POST", getBackendUrl() + '/api/processors/', true);



    const request = {
        'brand': document.getElementById('brand').value,
        'name': document.getElementById('name').value,
        'cpuClockSpeed': document.getElementById('cpuClockSpeed').value,
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request));
    
}