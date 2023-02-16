package ee.rainer.kodune1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class Kalkulaator {

    List<Integer> integers = new ArrayList<>(Arrays.asList(31, 52, 24));

    @GetMapping("getallnumbers") //localhost:8080/getallnumbers
    public List<Integer> getAllNumbers() {
        return integers;
    }

    @GetMapping("addnumber/{num}") //localhost:8080/addnumber/num
    public List<Integer> addNumber (@PathVariable int num) {
        integers.add(num);
        return integers;
    }

    @GetMapping("deletenumber/{index}") //localhost:8080/deletenumber/index
    public List<Integer> deleteNumber (@PathVariable int index) {
        integers.remove(index);
        return integers;
    }

    @GetMapping("clearnumbers") //localhost:8080/clearnumbers
    public List<Integer> clearNumbers () {
        integers.clear();
        return integers;
    }

    @GetMapping("addallnumbers") //localhost:8080/addallnumbers
    public int addAllNumbers () {
        int sum = 0;
        /**
         for (int i: integers) {
            sum += i;
         }
         **/

        for (int i = 0; i < integers.size(); i++) {
            sum = sum + integers.get(i);
        }

        return sum;
    }

    @GetMapping("averagenumber") //localhost:8080/averagenumber
    public int averageNumber () {
        int average = addAllNumbers() / integers.size();
        return average;
    }

    @GetMapping("numberamount") //localhost:8080/numbramount
    public int numberAmount () {
        return integers.size();
    }

    @GetMapping("add1toall") //localhost:8080/add1toall
    public List<Integer> addOneToAll () {
        for (int i = 0; i < integers.size(); i++) {
            integers.set(i, integers.get(i)+1);
        }
        return integers;
    }

    @GetMapping("addnumtoall/{num}") //localhost:8080/addnumtoall
    public List<Integer> addNumToAll (@PathVariable int num) {
        for (int i = 0; i < integers.size(); i++) {
            integers.set(i, integers.get(i)+num);
        }
        return integers;
    }

    @GetMapping("divideallby10") //localhost:8080/divideallby10
    public List<Integer> divideAllByTen () {
        for (int i = 0; i < integers.size(); i++) {
            integers.set(i, integers.get(i)/10);
        }
        return integers;
    }

    @GetMapping("divideallbynum/{num}") //localhost:8080/divideallbynum
    public List<Integer> divideAllByNum (@PathVariable int num) {
        for (int i = 0; i < integers.size(); i++) {
            integers.set(i, integers.get(i)/num);
        }
        return integers;
    }


    //.................


    @GetMapping("liida/{nr1}/{nr2}") //localhost:8080/liida/nr1/nr2
    public int liidaNumbrid (@PathVariable int nr1, @PathVariable int nr2) {
        int liidetudNum = nr1 + nr2;
        return liidetudNum;
    }

    @GetMapping("lahuta/{nr1}/{nr2}") //localhost:8080/lahuta/nr1/nr2
    public int lahutaNumbrid (@PathVariable int nr1, @PathVariable int nr2) {
        int lahutatudNum = nr1 - nr2;
        return lahutatudNum;
    }

    @GetMapping("korruta/{nr1}/{nr2}") //localhost:8080/korruta/nr1/nr2
    public int korrutaNumbrid (@PathVariable int nr1, @PathVariable int nr2) {
        int korrutatudNum = nr1 * nr2;
        return korrutatudNum;
    }

    @GetMapping("jaga/{nr1}/{nr2}") //localhost:8080/jaga/nr1/nr2
    public int jagaNumbrid (@PathVariable int nr1, @PathVariable int nr2) {
        int jagatudNum = nr1 / nr2;
        return jagatudNum;
    }

}
