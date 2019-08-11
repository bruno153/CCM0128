Como Polygon.java funciona:

Como sugerido, o programa identifica o ponto mais baixo em relação
ao eixo Y. Desta maneira, qualquer outro ponto do conjunto mais 
esse ponto mais baixo definem uma reta com um ângulo entre 0 e
pi. Então basta utilizar uma função trigonométrica que seja 
monotônica nesse intervalo, no caso cosseno. Utilizando um algoritmo
de sorting que utiliza cosseno como parâmetro, é possível resolver
a maioria dos problemas.

Entretanto, existem certas configurações de pontos colineares que
fazem com que ambos os pares tenham o mesmo valor de coseno. Para
evitar que o polígono resultante seja defeituoso, antes do insertion
sort por cosseno, é executado um insertion sort por distância em relação
ao ponto pivot. Isso faz com que para pontos com o mesmo valor de cosseno
o ponto mais extremo seja escolhido primeiro, formando um polígono adequado.

Por isso, para ambas as operações de sorting foi utilizado insertion sort.
Pois algoritmo não estável poderia arruinar o trabalho de sorting anterior.