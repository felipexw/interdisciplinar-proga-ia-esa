var $webSocket;
var $serverLocation = "ws://10.60.15.37:8080/interdisciplinar/game/";
var 
$index;
var $room = 'fudenica';
var $strId = 'formJogo:btn_';
var $contentValue = '"X"';
var $bPrimeiraJogada = true;
var $bJogou = false;
var $id;

function isAvailable() {
    return document.getElementById($strId + $index).value === " ";
}

function writeMessage(param) {
    var data = JSON.parse(param);
    console.log(param);

    if (data.id == $id)
        $bJogou = true;
    else
        $bJogou = false;
    
    if ($bPrimeiraJogada) {
        $bPrimeiraJogada = false;
    } else {
        $contentValue = ($contentValue == '"X"' ? '"O"' : '"X"');
    }
    document.getElementById($strId + data.buttonIndex).value = $contentValue;
}

function calculateIndex(i, j) {
    switch (i) {
        case 0:
            {
                if (j == 0)
                    $index = '0';
                else
                if (j == 1)
                    $index = '1';
                else
                    $index = '2';
            }
            break;
        case 1:
            {
                if (j == 0)
                    $index = '3';
                else
                if (j == 1)
                    $index = '4';
                else
                    $index = '5';
            }
            break;
        case 2:
            {
                if (j == 0)
                    $index = '6';
                else
                if (j == 1)
                    $index = '7';
                else
                    $index = '8';
            }
            break;
    }
}


function playPVP(i, j) {
    calculateIndex(i, j);
    if (isAvailable()) {
        if (!$bJogou) {
            var msg = '{"i":' + i + ', "j":'
                    + j + ', "id":' + $id + ',"buttonIndex": ' + $index + ',"contentValue": ' + $contentValue + '    }';
            $webSocket.send(msg);
        } else {
            alert("Não é a sua vez de jogar!");
        }
    } else {
        alert("Jogada inválida!");
    }
}

window.onload = function() {
    console.log("window.onload = function() {");
    $webSocket = new WebSocket($serverLocation + $room);
    $webSocket.onmessage = function(e) {
        writeMessage(e.data);
    }
    $webSocket.onopen = function(e) {
        console.log('ABriu conexão websocket');
    }
    $id = parseInt(Math.random() * Math.random() * 12345 / Math.random());
}

