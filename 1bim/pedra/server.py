from xmlrpc.server import SimpleXMLRPCServer
import random

def jogar_pedra_papel_tesoura(escolha_cliente):
    opcoes = ["Pedra", "Papel", "Tesoura"]
    escolha_servidor = random.randint(0, 2) 

    if escolha_cliente == escolha_servidor:
        resultado = "Empate"
    elif (
        (escolha_cliente == 0 and escolha_servidor == 2)
        or (escolha_cliente == 1 and escolha_servidor == 0)
        or (escolha_cliente == 2 and escolha_servidor == 1)
    ):
        resultado = "Você ganhou!"
    else:
        resultado = "Servidor ganhou!"

    return f"Servidor escolheu: {opcoes[escolha_servidor]}, {resultado}"

server = SimpleXMLRPCServer(("localhost", 8000))
server.register_function(jogar_pedra_papel_tesoura, "jogar")

print("Servidor RPC iniciado na porta 8000...")
server.serve_forever()
