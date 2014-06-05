function checkSquart(uiComponent) {
    debugger;
    if (uiComponent.outerText == "") {
        uiComponent.outerText = 'XXXXXXXXX';
        var index = parseInt(uiComponent.id.charAt(uiComponent.id.length - 1));
        var indexComputerPlay;

        switch (index) {
            case 0:
                Game.setMovimentoJogador(0, 0);
                break;

            case 1:
                Game.setMovimentoJogador(0, 1);
                break;

            case 2:
                Game.setMovimentoJogador(0, 2);
                break;

            case 3:
                Game.setMovimentoJogador(1, 0);
                break;

            case 4:
                Game.setMovimentoJogador(1, 1);
                break;

            case 5:
                Game.setMovimentoJogador(1, 2);
                break;

            case 6:
                Game.setMovimentoJogador(2, 0);
                break;

            case 7:
                Game.setMovimentoJogador(2, 1);
                break;

            case 8:
                Game.setMovimentoJogador(2, 2);
                break;
        }
        indexComputerPlay = Game.movimentoCompudador();
    }
}
