import sys


class Node():
    def __init__(self, value):
        self.value = value
        self.height = 0
        self.left = None
        self.right = None


class BinaryTree():
    def __init__(self, balance: bool):
        self.root = None
        self.balance = balance

    def insert(self, value):
        def get_height(node: Node) -> int:
            if node is None:
                return -1
            return node.height

        def calc_height(node: Node):
            if node:
                node.height = max(get_height(node.left),
                                  get_height(node.right)) + 1

        def balance_factor(node: Node):
            if node is None:
                return 0
            return get_height(node.left) - get_height(node.right)

        def left_rotation(node: Node) -> Node:
            old_right = node.right
            node.right = node.right.left
            old_right.left = node
            calc_height(node)
            calc_height(old_right)
            return old_right

        def right_rotation(node: Node) -> Node:
            old_left = node.left
            node.left = node.left.right
            old_left.right = node
            calc_height(node)
            calc_height(old_left)
            return old_left

        def balance(node: Node):
            if balance_factor(node) > 1:
                if balance_factor(node.left) > 0:  # LL
                    node = right_rotation(node)
                else:  # LR
                    node.left = left_rotation(node.left)
                    node = right_rotation(node)
            elif balance_factor(node) < -1:
                if balance_factor(node.right) < 0:  # RR
                    node = left_rotation(node)
                else:  # RL
                    node.right = right_rotation(node.right)
                    node = left_rotation(node)
            return node

        def insert_helper(node: Node):
            if node is None:
                return Node(value)
            if value <= node.value:
                node.left = insert_helper(node.left)
            else:
                node.right = insert_helper(node.right)

            if self.balance:
                node = balance(node)
            calc_height(node)
            return node

        self.root = insert_helper(self.root)

    def print(self, with_height=False):
        layer = [self.root]

        while len(layer) > 0:
            new_layer = []
            has_more_layer = False

            for i in range(len(layer)):
                node = layer[i]
                if node is None:
                    print('X', end='')
                    new_layer.append(None)
                    new_layer.append(None)
                else:
                    if with_height:
                        print('{0}({1})'.format(
                            node.value, node.height), end='')
                    else:
                        print(node.value, end='')
                    new_layer.append(node.left)
                    new_layer.append(node.right)
                    if node.left or node.right:
                        has_more_layer = True
                if i != len(layer) - 1:
                    print(' ', end='')

            if not has_more_layer:
                break
            print()
            layer = new_layer


if __name__ == '__main__':
    tree = BinaryTree(balance=True)  
    with open(sys.argv[1], 'r') as f:
        for num in f.readlines():
            tree.insert(int(num)) # if numbers.txt contains float, then change the int casting into float.

    tree.print()
