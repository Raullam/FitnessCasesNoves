const board = document.getElementById("board");
const video = document.getElementById("video");
const gridSize = 10; // Tamaño del tablero
const mineCount = 20; // Cantidad de minas
let foundBomb = false;

let gameBoard = [];

const createBoard = () => {
    // Crea el tablero y llena con casillas vacías.
    for (let row = 0; row < gridSize; row++) {
        const rowArray = [];
        for (let col = 0; col < gridSize; col++) {
            rowArray.push({
                isMine: false,
                isRevealed: false,
                isFlagged: false, // Agregado para manejar las banderas
                neighbors: 0
            });
        }
        gameBoard.push(rowArray);
    }
};

const plantMines = () => {
    // Coloca minas en ubicaciones aleatorias.
    for (let i = 0; i < mineCount; i++) {
        let row, col;
        do {
            row = Math.floor(Math.random() * gridSize);
            col = Math.floor(Math.random() * gridSize);
        } while (gameBoard[row][col].isMine);
        gameBoard[row][col].isMine = true;
    }
};

const revealCell = (row, col) => {
    if (row < 0 || col < 0 || row >= gridSize || col >= gridSize) {
        return; // Evitar desbordamiento del tablero.
    }

    const cell = gameBoard[row][col];
    if (cell.isRevealed || cell.isFlagged) {
        return; // No reveles una casilla ya revelada o marcada con bandera.
    }

    cell.isRevealed = true;

    const cellElement = document.querySelector(`[data-row="${row}"][data-col="${col}"]`);
    cellElement.classList.add('destapado'); // Agregar la clase 'destapado' para mostrar la celda como destapada

    if (cell.isMine) {
        // Si la celda es una bomba, mostrar el símbolo unicode de la bomba
        cellElement.classList.add('bomba'); // Agregar la clase para el fondo rojo
        cellElement.innerHTML = '&#128163;'; // Unicode del símbolo de la bomba
        // Reproducir la animación de video
        video.style.display = 'block'; // Mostrar el elemento de video
        video.currentTime = 0;
        video.play(); // Reproducir el video
        foundBomb = true;
        setTimeout(() => {
            video.style.display = 'none'; // Ocultar el video después de 2 segundos
        }, 6800);
    } else if (cell.neighbors === 0) {
        // Si la casilla no tiene minas vecinas, revela las casillas adyacentes.
        for (let r = row - 1; r <= row + 1; r++) {
            for (let c = col - 1; c <= col + 1; c++) {
                revealCell(r, c);
            }
        }
    } else {
        // Si la casilla tiene minas vecinas, mostrar el número correspondiente
        cellElement.innerHTML = cell.neighbors;
        cellElement.classList.add(`c${cell.neighbors}`); // Agregar clase para el color del número
    }
    bubbleSound.currentTime = 0;
    bubbleSound.play();
};

const calculateNeighbors = () => {
    // Calcula el número de minas vecinas para cada casilla.
    for (let row = 0; row < gridSize; row++) {
        for (let col = 0; col < gridSize; col++) {
            if (gameBoard[row][col].isMine) {
                continue;
            }

            for (let r = row - 1; r <= row + 1; r++) {
                for (let c = col - 1; c <= col + 1; c++) {
                    if (r >= 0 && c >= 0 && r < gridSize && c < gridSize && gameBoard[r][c].isMine) {
                        gameBoard[row][col].neighbors++;
                    }
                }
            }
        }
    }
};

const checkWin = () => {
    // Verifica si el jugador ha ganado.
    // El jugador gana si todas las casillas no minadas están reveladas.
    let unrevealedSafeCells = 0;
    for (let row = 0; row < gridSize; row++) {
        for (let col = 0; col < gridSize; col++) {
            if (!gameBoard[row][col].isMine && !gameBoard[row][col].isRevealed) {
                unrevealedSafeCells++;
            }
        }
    }

    if (unrevealedSafeCells === 0) {
        // El jugador ha ganado.
        // Muestra un mensaje de victoria.
    }
};

const toggleFlag = (row, col) => {
    const cell = gameBoard[row][col];
    const cellElement = document.querySelector(`[data-row="${row}"][data-col="${col}"]`);

    if (!cell.isRevealed) {
        cell.isFlagged = !cell.isFlagged; // Cambiar el estado de la bandera

        if (cell.isFlagged) {
            cellElement.classList.add('icon-bandera'); // Agregar clase para mostrar la bandera
        } else {
            cellElement.classList.remove('icon-bandera'); // Remover clase para quitar la bandera
        }
    }
};

const handleClick = (event) => {
    const row = parseInt(event.target.dataset.row);
    const col = parseInt(event.target.dataset.col);

    revealCell(row, col);
    checkWin();
};

const handleRightClick = (event) => {
    event.preventDefault(); // Evitar el menú contextual del botón derecho

    const row = parseInt(event.target.dataset.row);
    const col = parseInt(event.target.dataset.col);

    toggleFlag(row, col);
};

createBoard();
plantMines();
calculateNeighbors();

for (let row = 0; row < gridSize; row++) {
    for (let col = 0; col < gridSize; col++) {
        const cell = document.createElement("div");
        cell.classList.add("cell");
        cell.dataset.row = row;
        cell.dataset.col = col;
        cell.addEventListener("click", handleClick);
        cell.addEventListener("contextmenu", handleRightClick); // Agregar evento de clic derecho
        board.appendChild(cell);
    }
}