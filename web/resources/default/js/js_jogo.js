this.x = $(function() {
    debugger;
    var $serverLocation = "ws:localhost:8080/interdisciplinar/jogo";
    var $index = 0;
    var $strId = 'formJogo:btn_';
    var $contentValue = '"X"';
    var $bPrimeiraJogada = true;
    var $bJogou = false;
    var $id = 0;
    var socket = $.atmosphere;
    
    var cont = 0;
    for (var i = 0; i < 3; i++) {
        for (var j = 0; j < 3; j++) {
            var x = document.getElementById($strId + '' + cont);
            cont++;
            x.onclick = function() {
                calculateIndex($index, j);
                if (isAvailable()) {
                    if (!$bJogou) {
                        var msg = '{"i":' + i + ', "j":'
                                + j + ', "id":' + $id + ',"buttonIndex": ' + $index + ',"contentValue": ' + $contentValue + '}';
                        subSocket.push(jQuery.stringifyJSON(msg));
                    } else {
                        alert("Não é a sua vez de jogar!");
                    }
                } else {
                    alert("Jogada inválida!");
                }
            };
        }
    }



    // We are now ready to cut the request
    request = {url: $serverLocation,
        contentType: "application/json",
        transport: 'websocket'};

    request.onOpen = function(response) {
        console.log("conetou");
    };

    request.onMessage = function(response) {
        console.log("request.onMessage = function(response) {");
        var message = response.responseBody;
        try {
            var json = jQuery.parseJSON(message);
        } catch (e) {
            console.log('This doesn\'t look like a valid JSON: ', message);
            return;
        }
    };

    request.onClose = function(response) {
        console.log("request.onClose = function(response)");
    }

    request.onError = function(response) {
        console.log("request.onError = function(response)");
    };
    
    var subSocket = socket.subscribe(request);

    function addMessage(author, message, color, datetime) {
        content.append('<p><span style="color:' + color + '">' + author + '</span> @ ' +
                +(datetime.getHours() < 10 ? '0' + datetime.getHours() : datetime.getHours()) + ':'
                + (datetime.getMinutes() < 10 ? '0' + datetime.getMinutes() : datetime.getMinutes())
                + ': ' + message + '</p>');
    }
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
        console.log("function calculateIndex");
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
}
);











