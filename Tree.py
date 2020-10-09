"""
Universidade Federal de Pernambuco
IF 969 - Algoritmos e estruturas de dados
Aluno: Pedro Henrique Roseno Bastos Silva
Árvores binárias
"""


from avl import *

class Nodo:
    def __init__(self, chave, valor=None):
        self.chave = chave
        self.valor = valor
        self.direita = Arvore()
        self.esquerda = Arvore()

#Métodos comparativos comentados
"""
    def __lt__(self, nodo):

        if type(self) == type(nodo):
            if nodo.valor > self.valor:
                return True
            else:
                return False
        raise TypeError("Objetos de tipos diferentes")

    def __gt__(self, nodo):

        if type(self) == type(nodo): 
            if nodo.valor < self.valor:
                return True
            else:
                return False
        raise TypeError("Objetos de tipos diferentes")

    def __le__(self, nodo):

        if type(self) == type(nodo): 
            if nodo.valor >= self.valor:
                return True
            else:
                return False
        raise TypeError("Objetos de tipos diferentes")

    def __ge__(self, nodo):

        if type(self) == type(nodo):
            if nodo.valor <= self.valor:
                return True
            else:
                return False
        raise TypeError("Objetos de tipos diferentes")

    def __eq__(self, nodo):

        if type(self) == type(nodo): 
            if nodo.valor == self.valor:
                return True
            else:
                return False
        raise TypeError("Objetos de tipos diferentes")   

    def __ne__(self, nodo):

        if type(self) == type(nodo):  
            if nodo.valor != self.valor:
                return True
            else:
                return False
        raise TypeError("Objetos de tipos diferentes")
"""
class Arvore(AVL):
    def __init__(self, raiz=None):
        self.raiz = raiz
        self.balanco = 0
        self.altura = 0
        self.lista = []
    
    def __getitem__(self, chave):
        """
        Retorna um elemento na árvore
        """
        if chave == self.raiz.chave or chave == None:
            return self.raiz.valor
        else:
            ponteiro = self.raiz
            while ponteiro != None:
                if ponteiro.chave == chave:
                    return ponteiro.valor
                elif chave < ponteiro.chave:
                    ponteiro = ponteiro.esquerda.raiz
                elif chave > ponteiro.chave:
                    ponteiro = ponteiro.direita.raiz

            raise IndexError("Chave inexistente") 

    def __setitem__(self, chave, novoValor):
        """
        Encontra um elemento na árvore e altera o seu valor
        """

        if chave == self.raiz.chave or chave == None:
            self.raiz.valor = novoValor
            return self.raiz.valor
        else:
            ponteiro = self.raiz
            while ponteiro != None:
                if ponteiro.chave == chave:
                    return ponteiro.valor
                elif chave < ponteiro.chave:
                    ponteiro = ponteiro.esquerda.raiz
                elif chave > ponteiro.chave:
                    ponteiro = ponteiro.direita.raiz

            raise IndexError("Chave inexistente")

    def __str__(self):
        """
        Retorna uma string com as chaves da arvore em pré-ordem
        """
        arvore = self.ordem(self.raiz)
        self.lista = []
        return str(arvore)
    
    def __repr__(self):

        arvore = self.pre_ordem(self.raiz)
        self.lista = []
        return "Arvore({})".format(arvore)
    
    
    def inserir(self, chave, valor):
        """
        Insere um elemento na árvore
        """
        arvore = self.raiz
        if arvore is None:
            self.raiz = Nodo(chave, valor)
            self.raiz.esquerda = Arvore()
            self.raiz.direita = Arvore()
            print("Chave inserida")

        elif chave < arvore.chave:
            self.raiz.esquerda.inserir(chave, valor)

        elif chave > arvore.chave:
            self.raiz.direita.inserir(chave, valor)

        else:
            raise KeyError("Chave já se encontra na arvore")

        self.balancear()

    def __delitem__(self, chave):
        """
        Deleta um elemento da árvore
        """
        if self.raiz is not None:
            if self.raiz.chave == chave:
                if (self.raiz.esquerda.raiz is None) and (self.raiz.direita.raiz is None):
                    self.raiz = None
                elif self.raiz.esquerda.raiz is None:
                    self.raiz = self.raiz.direita.raiz
                elif self.raiz.direita.raiz is None:
                    self.raiz = self.raiz.esquerda.raiz
                else:
                    sucessor = self.sucessor(self.raiz)
                    if sucessor is not None:
                        self.raiz.chave = sucessor.chave
                        self.raiz.direita.__delitem__(sucessor.chave)
                self.balancear()
                return
            elif chave < self.raiz.chave:
                self.raiz.esquerda.__delitem__(chave)
            elif chave > self.raiz.chave:
                self.raiz.direita.__delitem__(chave)
            self.balancear()
        else:
            raise IndexError("Arvore vazia")

    def sucessor(self, raiz):
        raiz = raiz.direita.raiz
        if raiz != None:
            while raiz.esquerda != None:
                if raiz.esquerda.raiz is None:
                    return raiz
                else:
                    raiz = raiz.esquerda.raiz
        return raiz

    def predecessor(self, raiz):
        raiz = raiz.esquerda.raiz
        if raiz is not None:
            while raiz.direita.raiz is not None:
                if raiz.direta.raiz is none:
                    return raiz
                else:
                    raiz = raiz.direita.raiz
        return raiz

    def ordem(self, raiz):
        if raiz != None:
            self.ordem(raiz.esquerda.raiz)
            self.lista.append(raiz.chave)
            self.ordem(raiz.direita.raiz)
        return self.lista
    
    def ordem_valores(self, raiz):
        if raiz != None:
            self.ordem(raiz.esquerda.raiz)
            self.lista.append(raiz.valor)
            self.ordem(raiz.direita.raiz)
        return self.lista

    
    def pre_ordem(self, raiz):
        if raiz != None:
            self.lista.append(raiz.chave)
            self.pre_ordem(raiz.esquerda.raiz)
            self.pre_ordem(raiz.direita.raiz)
        return self.lista

    def pos_ordem(self, raiz):
        if raiz == None:
            return self.lista
        self.pos_ordem(raiz.esquerda.raiz)
        self.pos_ordem(raiz.direita.raiz)
        self.lista.append(raiz.chave)

    def __bool__(self):
        """
        Verifica se a árvore não está vazia
        """
        if self.raiz == None:
            return False
        else:
            return True
    
    def __contains__(self, chave):
        """
        Verifica se um elemento está na árvore
        """
        contido = self.__getitem__(chave)
        if contido is None:
            return False
        else:
            return True

    def __iter__(self):
        lista = self.pre_ordem(self.raiz)
        self.lista = []
        return Ponteiro(lista)

    def chaves(self):
        chaves = self.ordem(self.raiz)
        self.lista = []
        return chaves

    def valores(self):
        valores = self.ordem_valores(self.raiz)
        self.lista = []
        return valores
    
    def reiniciar(self):
        self.pos_ordem(self.raiz)
        for elemento in self.lista:
            print(elemento)
            self.__delitem__(elemento)


class AVL(Arvore):
    def balanceada(self):
        if (arvore.raiz.balanco < 2) and (arvore.raiz.balanco > -2):
            return True
        else:
            return False

    def calcula_altura(self):
        if not self.raiz is None:
            if recursao:
                if self.raiz.esquerda != None:
                    self.raiz.esquerda.calcula_altura()
                if self.raiz.direita != None:
                    self.raiz.direita.calcula_altura()

            self.altura = max(self.raiz.esquerda.altura,
                              self.raiz.direita.altura) + 1
        else:
            self.altura = -1
    
    def calcula_balanceamento(self, recurse=True):
        if not self.raiz is None:
            if recurse:
                if self.raiz.esquerda != None:
                    self.raiz.esquerda.calcula_balanceamento()
                if self.raiz.direita != None:
                    self.raiz.direita.calcula_balanceamento()

            self.balanco = self.raiz.esquerda.altura - self.raiz.direita.altura
        else:
            self.balanco = 0
    
    def gira_direita(self):
        raiz = self.raiz
        filho_esquerda = self.raiz.esquerda.raiz
        filho_esquerda_direita = filho_esquerda.direita.raiz
        self.raiz = filho_esquerda
        filho_esquerda.direita.raiz = raiz
        raiz.esquerda.raiz = filho_esquerda_direita
    
    def gira_esquerda(self):
        raiz = self.raiz
        filho_direita = self.raiz.direita.raiz
        filho_direita_esquerda = B.esquerda.raiz
        self.raiz = filho_direita
        filho_direita.esquerda.raiz = raiz
        raiz.direita.raiz = filho_direita_esquerda
    
    def balancear(self):

        self.calcula_altura(False)
        self.calcula_balanceamento(False)
        while abs(self.balanco) > 1:
            if self.balanco > 1:
                if self.raiz.esquerda.raiz.balanco < 0:
                    self.raiz.esquerda.gira_esquerda()  
                    self.calcula_altura()
                    self.calcula_balanceamento()
                self.gira_direita()

            if self.balanco < -1:
                if self.raiz.direita.raiz.balanco > 0:
                    self.raiz.direita.gira_direita() 
                    self.calcula_altura()
                    self.calcula_balanceamento()
                self.gira_esquerda()

            self.calcula_altura()
            self.calcula_balanceamento()

class Ponteiro:
    def __init__(self, lista):

        self.tamanho = len(lista)
        self.lista = lista
        self.indice = 0

    def __next__(self):
        if (self.indice < self.tamanho):
            posicao = self.lista[self.indice]
            self.indice += 1
            return posicao
        else:
            raise StopIteration("StopIt")

    def _iter_(self):
        return self

if __name__ == "__main__":

    arvore = Arvore()
    arvore.inserir(5, 'pedro')
    arvore.inserir(6, 'pedro')
    arvore.inserir(4, 'daniel')
    arvore.inserir(2, 'gabriel')
    arvore.inserir(1, 'mico')
    del arvore[4]
    del arvore[5]
    for elemento in arvore:
        print(elemento)


    

    
