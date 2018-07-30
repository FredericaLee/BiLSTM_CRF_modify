import copy

# data =[1,2,3,4]
# tensor = torch.FloatTensor(data)
# print(tensor)
# print(tensor.view(1,-1))

# some data processing function

#for train data 
def data_pre1():
    files = open('F:/intern/train/ji',encoding='UTF-8')
    train = []
    train_data=[]
    sen_x =[ ]          #句子x
    sen_y =[ ]          #句子y
    BATCH_SIZE = []
    for line in files:
        if not len(line) or line.isspace():
            x = copy.deepcopy(sen_x)
            y = copy.deepcopy(sen_y)
            train.append(x)
            train.append(y)
            med = tuple(copy.deepcopy(train))
            train_data.append(med)
            train = list(train)
            train.clear()
            BATCH_SIZE.append(len(sen_x))
            sen_x.clear()
            sen_y.clear()
        else :
            temp = line.split()
            sen_x.append(temp[0])
            sen_y.append(temp[1])

    train.append(sen_x)
    train.append(sen_y)
    med = tuple(train)
    train_data.append(med)
    BATCH_SIZE.append(len(sen_x))
    return train_data


# prepocess data for calculate.java
def filter():
    file =  open('F:/intern/test/LSTM_re','r',encoding='UTF-8')  #after demo
    file1 = open('F:/intern/test/LSTM_format','w+',encoding='UTF-8')  #output file
    file2 = open('F:/intern/test/jie','r',encoding= 'UTF-8')		#test file

    src = []
    label =[]

    #提取label
    for line in file:
        temp1 = line.split('[')
        temp2 = temp1[1].split(']')
        temp3 = temp2[0].split(', ')
        print(len(temp3))
        for i in temp3:
            if(i == "0"):
                i="B-LOC"
            if(i == "1"):
                i="I-LOC"
            if (i == "2"):
                i = "B-ORG"
            if (i == "3"):
                i = "I-ORG"
            if (i == "4"):
                i = "B-PER"
            if (i == "5"):
                i = "I-PER"
            if(i == "6"):
                i = "O"
            label.append(i)
        label.append("ss")

    #提取原文
    for line in file2:
        if not len(line) or line.isspace():
            src.append("ss")
            #print("ss")
        else:
            temp4 = line.split(" ")
            src.append(temp4[0])
            #print(temp4[0])

    #合并写入
    for (i1, i2) in  zip(src, label):
        if(i1 == "ss"):
            file1.write("\n")
        else:
            file1.write(i1+" "+i2+"\n")
            print(i1+" "+i2)

# for test data
def data_pre2():
    data_test = []
    test_t2 = []
    file_test = open('F:/intern/test/jie','r',encoding='UTF-8')
    for each in file_test:
        if not len(each) or each.isspace():
            h= copy.deepcopy(test_t2)
            data_test.append(h)
            test_t2.clear()
            #print()
        else:
            test_t1 = each.split()
            #print(test_t1[0])
            test_t2.append(test_t1[0])

    data_test.append(test_t2)
    return data_test


#
#filter()
#
# print(train[0][0], end =' ')
# print(len(train[0][0]))
# print(train[1][0],end =' ')
# print(len(train[1][0]))


