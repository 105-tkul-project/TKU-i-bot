import xlrd
import os
# 要打開的語料庫
wb = xlrd.open_workbook ('xml.xlsx')
# 要存入的路徑
base = os.getcwd ()+"\\data"

os.chdir (base)

# 打開每一個工作表(sheet)
for sheets in wb.sheet_names ():
    # create directories
    try:
        os.mkdir (sheets)
        # get in directory
        os.chdir (base + f"/{sheets}")
    except FileExistsError:
        os.chdir (base + f"/{sheets}")

    # get into sheet
    ws = wb.sheet_by_name (sheets)
    # iter rows
    for i in range (ws.nrows):
        # create txt with rows, overwrite if file exist
        with open (f"{i}.txt", 'w', encoding='utf-8') as f:
            f.write (f"{ws.cell (i, 0).value}")

    os.chdir (base)
