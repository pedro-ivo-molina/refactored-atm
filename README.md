# refactored-atm
O processo de refatoração desse projeto começou com a escolha da CleanArchitecture como padrão da aplicação.

O motivo da escolha dessa arquitetura foi pra mander as lógicas de negócio isoladas do resto, se comunicando somente via chamadas com os serviços. Cada serviço basicamente tem sua interface, assim diminuindo o acoplamento.

Há também um pacote chamado Factory, onde nele há a inicialização de todos os serviços assim também diminuindo o acoplamento, pois ao invés de chamar os serviços os domínios chamam a Factory.

O objetivo dessa refatoração foi melhorar o código dessa aplicação mas mantendo suas funcionalidades principais e comportamento atual, ou seja, foi melhorado a forma que o projeto foi implementado.