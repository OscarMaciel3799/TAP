def main():
    k = int(input())  
    stock = list(map(int, input().split()))  

    n = int(input()) 
    pedidos = [list(map(int, input().split())) for i in range(n)] 

    consumo_diario = [0] * k
    for i in range(n):
        for j in range(k):
            consumo_diario[j] += pedidos[i][j]

    dias_completos = float('inf')
    for j in range(k):
        if consumo_diario[j] > 0:
            dias_completos = min(dias_completos, stock[j] // consumo_diario[j])

    restante = []
    for j in range(k):
        valor = stock[j] - consumo_diario[j] * dias_completos
        restante.append(valor)

    
    consumos = [[0] * (n + 1) for _ in range(k)]
    for j in range(k):
        for i in range(n):
            consumos[j][i + 1] = consumos[j][i] + pedidos[i][j]

    for i in range(1, n + 1):
        for j in range(k):
            if consumos[j][i] > restante[j]:
                print(dias_completos + 1)
                print(i)
                return

if __name__ == "__main__":
    main()
