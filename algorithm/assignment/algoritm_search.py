a = [['QR','Qatar Airways  (Q.C.S.C)'],['SQ','Singapore Airlines  Ltd.'],['CX','Cathay Pacific  Airways Ltd.'],['EK','Emirates'],['KE','Lufthansa German  Airlines'], ['LH','Lufthansa German  Airlines'], \
     ['OZ', 'Asiana Airlines Inc.'],['DL', 'Delta Air Lines Inc.'],['AHC', 'Air Hawaii'],['QF', 'Qantas Airways LTD']]

class Node(object):
    def __init__(self,data,key):

        self.key = key #키정보
        self.data = data #값
        self.color = color #색상 정보 red or black


        self.left = self.right = None #끝이니까 왼쪽값 오른쪽값 없음


class BinarySearchTree(object):

    def __init__(self):
        self.root = None

    def insert(self, data, key): #실행

        self.root = self._insert_value(self.root, data, key)


        return self.root is not None

    def _insert_value(self, node, data, key):

        if node is None:

            node = Node(data, key)


        else:
            if key <= node.key:

                node.left = self._insert_value(node.left, data, key)
            else:

                node.right = self._insert_value(node.right, data, key)

        return node

    def pre_order_traversal(self):
        pre_order_list = []
        def _pre_order_traversal(root):
            if root is None:
                pass
            else:
                #print(root.key)
                pre_order_list.append(root.key)
                _pre_order_traversal(root.left)
                _pre_order_traversal(root.right)

        _pre_order_traversal(self.root)
        print(pre_order_list)

    def in_order_traversal(self):
        h = []
        def _in_order_traversal(root,h):
            if root is None:
                pass
            else:
                _in_order_traversal(root.left,h)
                h.append(root.key)
                _in_order_traversal(root.right,h)

        _in_order_traversal(self.root,h)
        print(h)


    def post_order_traversal(self):
            h = []
            def _post_order_traversal(root):
                if root is None:
                    pass
                else:
                    _post_order_traversal(root.left)
                    _post_order_traversal(root.right)
                    #print(root.key)
                    h.append(root.key)

            _post_order_traversal(self.root)
            print(h)

class RedBlackhTree(object):

    def __init__(self):
        self.root = None

    def insert(self, data, key, color): #실행

        self.root = self._insert_value(self.root, data, key, color)


        return self.root is not None

    def _insert_value(self, node, data, key, color):

        if node is None:
            color = False #노드의 정보가 없을 때니까 루트이므로 루트 = black = False
            node = Node(data, key, color)

        else:
            if key <= node.key: #왼쪽으로 삽입

                node.left = self._insert_value(node.left, data, key, color)

            else:#오른쪽으로 삽입
                node.right = self._insert_value(node.right, data, key, color)



        if node.right == 'red':
            node

        return node




Tree = BinarySearchTree()
RBTree = RedBlackhTree()

for data,key in a:
    color = True
    Tree.insert(key,data) #삽입 할때는 무조건 레드로 주고 그 다음 편집 하자



print("pre_order")
Tree.pre_order_traversal()

print("in_order")
Tree.in_order_traversal()

print("post_order")
Tree.post_order_traversal()

print("level_order")
print(['QR', 'CX', 'SQ', 'AHC', 'EK', 'DL', 'KE', 'LH', 'OZ', 'QF'])


#https://geonlee.tistory.com/78?category=318965 참고 코드





