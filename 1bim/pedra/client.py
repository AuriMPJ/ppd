import xmlrpc.client

def validar_entrada(escolha_str):
    if escolha_str.lower() == "sair":
        return "sair"
    
    try:
        escolha_int = int(escolha_str)
        if escolha_int >= 0 and escolha_int <= 2:
            return escolha_int
    except ValueError:
        pass
    
    return None

proxy = xmlrpc.client.ServerProxy("http://localhost:8000/")

while True:
    escolha_str = input("Escolha Pedra (0), Papel (1) ou Tesoura (2) (ou 'sair' para sair): ")
    
    escolha = validar_entrada(escolha_str)
    
    if escolha is None:
        print("Escolha inválida. Por favor, insira um número válido (0 para Pedra, 1 para Papel, 2 para Tesoura) ou 'sair'.")
        continue

    if escolha == "sair":
        break

    resultado = proxy.jogar(escolha)
    print(resultado)

print("Jogo encerrado.")
