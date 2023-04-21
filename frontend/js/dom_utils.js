export function clearElementChildren(element) {
    while (element.firstChild) {
        element.removeChild(element.firstChild);
    }
}

export function createLinkCell(text, href) {
    let td = document.createElement('td');
    let a = document.createElement('a');
    a.textContent = text;
    a.href = href;
    a.classList.add('link-cell');
    td.appendChild(a);
    return td;
}

export function createButtonCell(text, onClick) {
    let td = document.createElement('td');
    let button = document.createElement('button');
    button.textContent = text;
    button.classList.add('button-cell');
    button.addEventListener('click', onClick);
    td.appendChild(button);
    return td;
}

export function createTextCell(text) {
    const td = document.createElement('td');
    td.classList.add('text-cell');
    td.appendChild(document.createTextNode(text));
    return td;
}

export function getParameterByName(name) {
    return new URLSearchParams(window.location.search).get(name);
}

export function setTextNode(id, text) {
    let element = document.getElementById(id);
    clearElementChildren(element);
    element.appendChild(document.createTextNode(text));
}