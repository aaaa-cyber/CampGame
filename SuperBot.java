/**
 * 負けないBotクラス
 */
class SuperBot {
    /**
     * ビンゴとなる線が含む座標を{h1,v1,h2,v2,h3,v3}として
     * 格納した配列cooLine
     * "coo"rdinates on the "Line" for bingo
     */
    final static int[][] cooLine = {
            {0,0,0,1,0,2},
            {1,0,1,1,1,2},
            {2,0,2,1,2,2},
            {0,0,1,0,2,0},
            {0,1,1,1,2,1},
            {0,2,1,2,2,2},
            {0,0,1,1,2,2},
            {2,0,1,1,0,2}
    };

    /**
     * リーチしているplayerのplayerNumを
     * checkLine()[line]に格納するメソッド。
     *
     * @param field　盤面
     * @return インデックスline番目にリーチしているplayerNumを、
     *          リーチのない場合は0を格納
     */
    static int[] checkLine(int[][] field) {
        //返り値となる変数lineを定義
        int[] line = new int[cooLine.length];

        //それぞれのlineの評価
        for (int i = 0; i < cooLine.length; i++) {
            /*
             * ビンゴとなるline上の値が
             * 0,1,1または0,2,2の場合はリーチとなるから、
             * その場合座標の値をline[index]に格納する。
             */
            int one = 2;
            int two = 2;
            int zero = 1;
            for (int j = 0; j < 3; j++) {
                switch (field[cooLine[i][2 * j]][cooLine[i][2 * j + 1]]) {
                    case 0: zero--; break;
                    case 1: one--;  break;
                    case 2: two--;  break;
                }
            }
            if (zero == 0 && one == 0) {
                //0,1,1の場合1を格納
                line[i] = 1;
            } else if (zero == 0 && two == 0) {
                //0,2,2の場合2を格納
                line[i] = 2;
            }

        }
        return line;
    }

    /**
     * リーチしているとわかっているlineを引数として受け取り、
     * あとどの座標におけばビンゴするかを返すメソッド。
     *
     * @param field　盤面
     * @param line　 リーチしていることがわかっているline
     * @return インデックス0にh座標、1にv座標を格納
     */
    static int[] toWin(int[][] field, int line) {
        //返り値を定義
        int[] ans = new int[2];

        //line上の座標で石の置かれていない座標を格納
        for(int i = 0; i < 3; i++) {
            if(field[cooLine[line][2 * i]][cooLine[line][2 * i + 1]] == 0) {
                ans[0] = cooLine[line][2 * i];
                ans[1] = cooLine[line][2 * i + 1];
                break;
            }
        }
        return ans;
    }

    /**
     * int[]型の配列のインスタンスをコピーするメソッド。
     *
     * @param ary　コピー元のインスタンスへのポインタ
     * @return コピーしたインスタンス
     */
    static int[] cloneAry(int[] ary) {
        int[] ans = new int[ary.length];
        for (int i = 0; i < ary.length; i++) {
            ans[i] = ary[i];
        }
        return ans;
    }

    /**
     * int[][]型の配列のインスタンスをコピーするメソッド。
     * メソッドcloneAryの多重定義とする。
     *
     * @param ary　コピー元のインスタンスへのポインタ
     * @return コピーしたインスタンス
     */
    static int[][] cloneAry(int[][] ary) {
        int[][] ans = new int[ary.length][];
        for (int i = 0; i < ary.length; i++) {
            ans[i] = cloneAry(ary[i]);
        }
        return ans;
    }

    /**
     * 候補の座標を受け取り、
     * リーチとなる場所の一つを返すメソッド。
     * 受け取るリストは、それぞれのインデックスに
     * 0 h1
     * 1 v1 ___1つ目
     * 2 h2
     * 3 v2 ___2つ目
     * ...
     * を格納したものとする。
     * (h2,v2)がリーチをつくる座標の場合、
     * h2のインデックス2を返す。
     * リーチを作る座標が見つからない場合は
     * -1を返す。
     *
     * @param playerNum プレイヤー識別子
     * @param list　　　 受け取る座標のリスト
     * @param field　   盤面
     * @return    リーチとなる場所の一つ
     */
    static int aboutToWin(int playerNum, int[] list, int[][] field) {
        //返り値を定義
        int ans = -1;

        /*
         * そこにおいたと仮定した座標
         * "as"su"m"ed "field"
         */
        int[][] asmField = cloneAry(field);

        //リストの座標に石を置いていく
        LOOP : for (int i = 0; i < (list.length / 2); i++) {
            //重複していないか
            if (asmField[list[2 * i]][list[(2 * i) + 1]] == 0) {
                //石を置く
                asmField[list[2 * i]][list[(2 * i) + 1]] = playerNum;
                //リーチしているか判定
                for (int j = 0; j < cooLine.length; j++) {
                    if (checkLine(asmField)[j] == playerNum) {
                        //リーチがあればそのh座標のインデックスを返す
                        ans = 2 * i;
                        break LOOP;
                    }
                }
            }
        }
        return ans;
    }

    /**
     * ダブルリーチとなる場所を探すメソッド。
     *
     * @param playerNum ダブルリーチとなる座標を探したいプレイヤー
     * @param field　　　盤面
     * @return インデックス0に求める座標があれば1,なければ0
     *         1にh座標、2にv座標
     */
    static int[] doubleAboutToWin(int playerNum, int[][] field) {
        //返り値を定義。
        int[] ans = new int[3];

        //空きに石を置いてみる
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == 0) {
                    /*
                     * そこにおいたと仮定した座標
                     * "as"su"m"ed "field"
                     */
                    int[][] asmField = cloneAry(field);
                    asmField[i][j] = playerNum;

                    //リーチの数のカウントをする。
                    int count = 0;
                    for (int k = 0; k < cooLine.length; k++) {
                        if (checkLine(asmField)[k] == playerNum) count++;
                    }

                    /*
                     * 返り値が2以上の場合、
                     * ダブルリーチとなる座標があったことと
                     * その座標をansに格納
                     */
                    if (count >= 2) {
                        ans[0] = 1;
                        ans[1] = i;
                        ans[2] = j;
                    }
                }
            }
        }
        return ans;
    }

    /**
     * listに格納された値のうち、
     * 置ける場所があればcooPutの形式で
     * 座標を返す。
     *
     * @param list　　座標リスト
     * @param field　 盤面
     * @return cooPut形式の座標
     */
    static int[] putList(int[] list, int[][] field) {
        //返り値の定義
        int[] ans = new int[3];

        //重複判定
        for (int i = 0; i < (list.length / 2); i++) {
            if (field[list[2 * i]][list[(2 * i) + 1]] == 0) {
                //重複がなければ石を置く
                ans[0] = 1;
                ans[1] = list[2 * i];
                ans[2] = list[(2 * i) + 1];
                break;
            }
        }
        return ans;
    }

    /**
     * 負けないBot本体
     *
     * @param playerNum　このBotの識別子
     * @param field　　　 盤面
     */
    static void superBot(int playerNum, int[][] field) {
        //相手の識別子を格納する変数enemy
        int enemy = playerNum == 1 ? 2 : 1;

        /*
         * 置く場所をcooPutとして定義する。
         * 各インデックスに次の値を格納する。
         * 0:置く場所が決まっているかどうか。決まっていれば1を格納。
         * 1:h座標
         * 2:v座標
         */
        int[] cooPut = new int[3];
        //i番目のlineにリーチがあるか
        for(int i = 0; i < cooLine.length; i++) {
            /*
             * checkLine(field)[i]は、
             * 「i番目のlineに
             * 　リーチがなければ0
             * 　リーチがあれば1,2」
             * を格納している。
             * リーチがあれば座標を決定するが、
             * 相手より自分のそれが優先される。
             */
            if(checkLine(field)[i] == playerNum) {
                //自分にリーチがあれば座標を即決定。
                cooPut[0] = 1;
                cooPut[1] = toWin(field,i)[0];
                cooPut[2] = toWin(field,i)[1];
                break;
            }else if(checkLine(field)[i] == enemy) {
                /*
                 * 相手にリーチがあれば、
                 * 座標を入れはするがループは脱しない。
                 * これは、後のlineに
                 * 自分のリーチがあるかもしれないからである。
                 */
                cooPut[0] = 1;
                cooPut[1] = toWin(field,i)[0];
                cooPut[2] = toWin(field,i)[1];
            }
        }

        //座標が決定されてないか
        if (cooPut[0] == 0) {
            // 中央が空いていたら中央に置く。
            if (field[1][1] == 0) {
                cooPut[0] = 1;
                cooPut[1] = 1;
                cooPut[2] = 1;
            }
        }

        //座標が決定されてないか
        if (cooPut[0] == 0) {
            /*
             * 自分が後手かつ石の並びが斜め一直線で、
             * 相手の石が自分の石を挟むようにおいてないか
             */
            if (field[1][1] == playerNum) {
                if (field[0][0] == enemy && field[2][2] == enemy
                    || field[2][0] == enemy && field[0][2] == enemy) {
                    //任意の辺に置く。簡単のため、ここでは(2,1)とする。
                    cooPut[0] = 1;
                    cooPut[1] = 2;
                    cooPut[2] = 1;
                }
            }
        }

        //座標が決定されていないか
        if (cooPut[0] == 0) {
            /*
             * 次に置いたらダブルリーチになる場所があれば
             * 座標を決定する。
             * こちらも自分優先。
             */
            if (doubleAboutToWin(playerNum, field)[0] == 1) {
                cooPut[0] = 1;
                cooPut[1] = doubleAboutToWin(playerNum, field)[1];
                cooPut[2] = doubleAboutToWin(playerNum, field)[2];
            } else if (doubleAboutToWin(enemy, field)[0] == 1) {
                cooPut[0] = 1;
                cooPut[1] = doubleAboutToWin(enemy, field)[1];
                cooPut[2] = doubleAboutToWin(enemy, field)[2];
            }
        }

        //座標が決定されていないか
        if (cooPut[0] == 0) {
            //角の座標のリスト
            int[] list = {0,0,2,0,0,2,2,2};

            /*
             * リーチのできる角に置く
             */
            if (aboutToWin(playerNum, list, field) != - 1) {
                cooPut[0] = 1;
                cooPut[1] = list[aboutToWin(playerNum, list, field)];
                cooPut[2] = list[aboutToWin(playerNum, list, field) + 1];
            } else {
                //リーチがない
                cooPut = putList(list, field);
            }
        }

        //座標が決定されていないか
        if (cooPut[0] == 0) {
            //辺の座標のリスト
            int[] list = {0,1,1,0,1,2,2,1};

            //辺に置く
            cooPut = putList(list, field);
        }

        //石を置く
        field[cooPut[1]][cooPut[2]] = playerNum;

    }
}