# coding: utf-8

from __future__ import print_function

import os
import tensorflow as tf
import tensorflow.contrib.keras as kr

from cnn_model import TCNNConfig, TextCNN
from rnn_model import TRNNConfig, TextRNN
from data.cnews_loader import read_category, read_vocab
# -----------------------------------------------------
from fuzzywuzzy import fuzz
from fuzzywuzzy import process
import xlrd
import socket
from _thread import start_new_thread

# -----------------------------------------------------
try:
    bool (type (unicode))
except NameError:
    unicode = str
# data path(model,Q&A database)
base_dir = 'data/cnews'
vocab_dir = os.path.join (base_dir, 'cnews.vocab.txt')

save_dir = 'checkpoints/textrnn'
save_path = os.path.join (save_dir, 'best_validation')  # 最佳验证结果保存路径


# keep
class CnnModel:
    def __init__(self):
        self.config = TRNNConfig ()  # <--TCNNConfig
        self.categories, self.cat_to_id = read_category ()
        self.words, self.word_to_id = read_vocab (vocab_dir)
        self.config.vocab_size = len (self.words)
        self.model = TextRNN (self.config)  # <--TextCNN

        self.session = tf.Session ()
        self.session.run (tf.global_variables_initializer ())
        saver = tf.train.Saver ()
        saver.restore (sess=self.session, save_path=save_path)  # 读取保存的模型

    def predict(self, message):
        # 支持不论在python2还是python3下训练的模型都可以在2或者3的环境下运行
        content = unicode (message)
        data = [self.word_to_id[x] for x in content if x in self.word_to_id]

        feed_dict = {
            self.model.input_x: kr.preprocessing.sequence.pad_sequences ([data], self.config.seq_length),
            self.model.keep_prob: 1.0
        }
#       # y_pred_cls = tf.argmax(tf.nn.softmax(self.logits), 1)  # 预测类别
        y_pred_cls = self.session.run (self.model.y_pred_cls, feed_dict=feed_dict)
        return self.categories[y_pred_cls[0]]


# type(Q) == str
def demo_test(Question):
    # ---get message---start replying
    print (f"The input from front-end :{Question}。")
    # 分類問題_debug
    print (f"LSTM model predict the input belongs to {cnn_model.predict (Question)} category.")
    # 分類問題類別(10類)，開啟該工作表
    ws = wb.sheet_by_name (cnn_model.predict (Question))
    # init and reset & reuse dictionary
    f = {}
    # iterate rows from A1 to B2 in dictionary f
    for j in range (ws.nrows):
        # key:question,val:answer, f = {question:answer,q:a}
        f[ws.cell (j, 0).value] = ws.cell (j, 1).value
    # 取得最相似的問題
    answer = process.extractOne (Question, [questions for questions in f], scorer=fuzz.partial_token_sort_ratio)
    # (最相似的問題,相似度)_debug
    print (answer)

    # 二次比對問題相似度_debug
    print (f"This is the accuracy from fuzz.partial_token_sort_ratio: {fuzz.partial_token_sort_ratio (answer[0], Question)}")

    # 依照最佳回應程度分別做回應
    if answer[1] > 60 and fuzz.token_set_ratio (answer[0], Question) > 60:
        print (f"The best reply : {f.get (answer[0])}.")
        return f.get (answer[0])
    elif answer[1] > 50:
        print (f"The minimum accuracy reply : {f.get (answer[0])}.")
        return "也許你要的是" + f.get (answer[0])
    else:
        print ("Bad accuracy")
        return "抱歉，我不確定你在說什麼。"


# single thread socket connection
def clientthread(connection):
    while True:
        connection.settimeout (None)
        # type(data)==bytes
        data = connection.recv (1024)
        while len (data) is 0:
            pass

        # response to client
        connection.send (bytes (demo_test (data.decode ('UTF-8')), encoding='utf-8'))
    # Server closed

if __name__ == '__main__':
    # loading...
    # open .xml file
    wb = xlrd.open_workbook ('xml.xlsx')
    # loading model
    cnn_model = CnnModel ()
    # ---loading complete---
    HOST = "192.168.100.22"  # see shell>ipconfig
    PORT = 8000
    print (HOST)
    # starting up socket ...
    with socket.socket (socket.AF_INET, socket.SOCK_STREAM) as s:
        s.bind ((HOST, PORT))
        s.listen ()
        while 1:
            # ---linking complete---wait for client connection...
            print ("listen for client...")
            connect, addr = s.accept ()
            print ("get client")
            # create new thread for every single client
            start_new_thread (clientthread, (connect,))
