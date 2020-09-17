class RBTNode:
    def __init__(self, data):
        self.data = data
        self.left = None
        self.right = None
        # red = True, black = False
        # new node always red
        self.color = True
        # self.Red = True
        # self.Black = False

    def inOrder(self, result=None):
        if result == None:
            result = []

        if self.left:
            self.left.inOrder(result)

        result.append(self.data)

        if self.right:
            self.right.inOrder(result)

        return result


class LLRB:
    def __init__(self):
        self.root = None

    def __isRed(self, node):
        if node == None:
            return False
        return node.color == True

    def insert(self, data):
        if self.root == None:
            self.root = RBTNode(data)
        else:
            self.root = self.__insert(self.root, data)
            self.root.color = False

    def __insert(self, root, node):
        if root == None:
            return RBTNode(node)
        if self.__isRed(root.left) and self.__isRed(root.right):
            self.flipColors(root)  # If both children are red, then colorflip
        if node < root.data:
            root.left = self.__insert(root.left, node)
        elif node > root.data:
            root.right = self.__insert(root.right, node)
        if self.__isRed(root.right) and not self.__isRed(root.left):
            root = self.rotateLeft(root)
        if self.__isRed(root.left) and self.__isRed(root.left.left):
            root = self.rotateRight(root)
        return root

    def rotateLeft(self, node):
        x = node.right
        node.right = x.left
        x.left = node
        x.color = x.left.color
        x.left.color = True
        return x

    def rotateRight(self, node):
        x = node.left
        node.left = x.right
        x.right = node
        x.color = x.right.color
        x.right.color = True
        return x

    def flipColors(self, node):
        node.color = not node.color
        node.left.color = not node.left.color
        node.right.color = not node.right.color

    def isRed(self, node):
        x = self.root
        while x.data != None:
            if x.data == node:
                return x.color == True
            elif node < x.data:
                x = x.left
            elif node > x.data:
                x = x.right
        return -1





    def Search(self, node):
        """Public function to lookup node, Returns True iff node is in tree."""
        count = 1
        node = self._Search(self.root, node,count)
        if node:

            return True
        else:

            return False

    def _Search(self, root, node, count):

        if root == None:
            print(count)
            return None
        if root.data == node:
            print(count)
            return node
        if node < root.data:
            count += 1
            return self._Search(root.left, node, count)
        else:  # so node > root.data
            count += 1
            return self._Search(root.right, node, count)

    def fixUp(self):
        self.__fixUp(self.root)

    def __fixUp(self, cur_node):
        if self.__isRed(cur_node.right):
            cur_node = self.rotateLeft(cur_node)
        if (self.__isRed(cur_node.left) and self.__isRed(cur_node.left.left)):
            cur_node = self.rotateRight(cur_node)
        if (self.__isRed(cur_node.left) and self.__isRed(cur_node.right)):
            self.flipColors(cur_node)

        return cur_node

    def _delete_node(self, node, obj):
        """Private function for traversing tree to delete obj. Perform movements and rotations during traversal to preserve RBT properties."""
        if node == None:
            return node
        if obj < node.data:
            if (not self.__isRed(node.left) and not self.__isRed(node.left.left)):  # node.left == None or
                node = self._moveRedLeft(node)
            node.left = self._delete_node(node.left, obj)
        else:
            if self.__isRed(node.left):
                node = self.rotateRight(node)
            if obj == node.data and node.right == None:
                return None
            if (not self.__isRed(node.right) and not self.__isRed(node.right.left)):  # node.right == None or
                node = self._moveRedRight(node)
            if obj == node.data:
                node.data = self._Search(node.right, self._min(node.right))
                node.right = self._deleteMin(node.right)
            else:
                node.right = self._delete_node(node.right, obj)
        return self.__fixUp(node)

    def _deleteMin(self, node):
        """Delete the minimum decendant of node. Performs movements during traversal to preserve RBT properties."""
        if node.left == None:
            return None
        if (not self.__isRed(node.left) and not self.__isRed(node.left.left)):  # node.left == None or
            node = self._moveRedLeft(node)
        node.left = self._deleteMin(node.left)
        return self.__fixUp(node)

    def _min(self, node):
        """Return the data of the minimum descendant of node."""
        while node.left != None: node = node.left
        if node == None:
            return None
        else:
            return node.data

    def _moveRedLeft(self, node):
        """Moves red node left. Used internally to preserve RBT properties."""
        self.flipColors(node)
        if node.right and self.__isRed(node.right.left):
            node.right = self.rotateRight(node.right)
            node = self.rotateLeft(node)
            self.flipColors(node)
        return node

    def _moveRedRight(self, node):
        """Moves red node right. Used internally to preserve RBT properties."""
        self.flipColors(node)
        if node.left and self.__isRed(node.left.left):
            node = self.rotateRight(node)
            self.flipColors(node)
        return node

    def delete(self, obj):
        """Public function for deleting node containing obj."""
        if self.root == None:
            return
        else:
            self.root = self._delete_node(self.root, obj)


def TestInsert():
    # testing insert function
    llrb = LLRB()
    for i in range(50):
        # insert function
        llrb.insert(i)

    # returns the nodes of tree inorder
    # not necessary to run
    print(llrb.root.inOrder())

    # for graphical representation of the tree
    plot_tree(llrb.root, figsize=(10, 2))


def TestDelete():
    # testing delete function
    llrb = LLRB()
    for i in range(50):
        llrb.insert(i)

    # not necessary to run
    print(llrb.root.inOrder())

    plot_tree(llrb.root, figsize=(10, 2))
    llrb.delete(50)
    plot_tree(llrb.root, figsize=(10, 2))


def TestSearch():
    # testing search function
    llrb = LLRB()
    for i in range(50):
        llrb.insert(i)

    print(llrb.Search(46))
    print(llrb.Search(100))


import matplotlib.pyplot as plt
from matplotlib import rcParams
import pylab


# a graphical representation of the left leaning red black tree (plot_tree, below, uses plot_node)
def plot_node(node, rb=True, level=1, posx=0, posy=0):
    width = 2000.0 * (0.5 ** (level))  # This will be used to space nodes horizontally
    if node.color == 0 or rb == False:
        plt.text(posx, posy, str(node.data), horizontalalignment='center', color='k', fontsize=10)
    else:
        plt.text(posx, posy, str(node.data), horizontalalignment='center', color='r', fontsize=10)

    if node.left:
        px = [posx, posx - width]
        py = [posy - 1, posy - 15]
        if node.left.color == 0 or rb == False:
            plt.plot(px, py, 'k-')  # , hold=True
        else:
            plt.plot(px, py, 'r-')  # , hold=True
        plot_node(node.left, rb, level + 1, posx - int(width), posy - 20)

    if node.right:
        plot_node(node.right, rb, level + 1, posx + int(width), posy - 20)
        px = [posx, posx + width]
        py = [posy - 1, posy - 15]
        if node.right.color == 0 or rb == False:
            plt.plot(px, py, 'k-')  # , hold=True
        else:
            plt.plot(px, py, 'r-')  # , hold=True


def plot_tree(node, figsize=(10, 6)):
    if node.color == True:
        rb = False
    else:
        rb = True
    rcParams['figure.figsize'] = figsize
    fig, ax = plt.subplots()
    ax.axis('off')
    plot_node(node, rb)

    plt.show()



movie= [] #무비 리스트 선언
# with open(r'C:\Users\JBC\Desktop\CARRIERS.txt','r') as f:
#         for movie_list in f: #파일에서 모든 영화 제목들 가져옴
#             movie_list = movie_list.rstrip('\n') #줄바꿈 기준으로 분리
#             movie_list_value = movie_list.split('\t')#tab기준으로 리스트
#             #movie_list_value[1] = int(movie_list_value[1])#리스트화 된 파일의 1을 정수로 바꿔준다.(영화 개봉연도)
#             movie.append(movie_list_value)
#


# testing insert function
llrb = LLRB()
a = 'ACDEKLOQRS'
movie = ['D','I','J','K','S','T','R','A']
for i in movie:
    # insert function
    llrb.insert(i[0])

# returns the nodes of tree inorder
# not necessary to run
print(llrb.root.inOrder())

print(llrb.Search('12Q'))
print(llrb.Search('02Q'))



# for graphical representation of the tree
plot_tree(llrb.root, figsize=(10, 2))

