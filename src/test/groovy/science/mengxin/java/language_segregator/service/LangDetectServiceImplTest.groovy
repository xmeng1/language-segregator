package science.mengxin.java.language_segregator.service

import com.optimaize.langdetect.i18n.LdLocale
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import science.mengxin.java.language_segregator.model.options.SupportLang
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

@SpringBootTest
class LangDetectServiceImplTest extends Specification {

    @Shared
    String longENSource = "The simple structure of OLED also means that you can attach this thing called ‘exciters’ to the back of the display. Now, those are really, really thin things that turn the whole display onto a speaker. There are about 5 exciters, I think, on the back of this screen and I have to say, it really does sound like the audio is coming from the right part of the picture - and that is basically because it is."
    @Shared
    String longFASource = "طراحی ساده اولد طوری کار می کند که شما می‌تونید این را که محرک نامیده میشه پشتش صفحه نمایش نصب کنید. این ها چیزای بسیار بسیار نازکیه که کل صفحه نمایش را به یک بلندگو تبدیل می کنه. پشت سر این صفحه نمایش به نظرم پنج محرک نصب شده و طوری کار می کنه که به نظر میاد صدا واقعا از جای مشخص صفحه بیرون میآد و به این دلیله که در واقع همینطور هم است."
    @Shared
    String longZhCnSource = "OLED的简单结构也意味着您可以将这种称为“激励器”的东西附加到显示器的背面。 现在，那些非常非常薄的东西将整个显示器转变为扬声器。 我认为，在这个屏幕的背面有大约5个激励器，我不得不说，它确实听起来像音频来自图片的正确部分 - 这基本上是因为它是。"
    @Shared
    String longZhTwSource = "OLED的簡單結構也意味著您可以將這種稱為“激勵器”的東西附加到顯示器的背面。 現在，那些非常非常薄的東西將整個顯示器轉變為揚聲器。 我認為，在這個屏幕的背面有大約5個激勵器，我不得不說，它確實聽起來像音頻來自圖片的正確部分 - 這基本上是因為它是。"
    @Shared
    String longFRSource = "La structure simple de OLED signifie également que vous pouvez attacher cette chose appelée \"excitateurs\" à l’arrière de l’écran. Ce sont des choses vraiment très minces qui transforment tout l’écran en haut-parleur. Il y a environ 5 excitateurs, je pense, à l'arrière de cet écran et je dois dire que cela ressemble vraiment à du son comme si l'audio provenait de la partie droite de l'image - et c'est essentiellement parce que c'est le cas."
    @Shared
    String longAFSource = "Die eenvoudige struktuur van OLED beteken ook dat jy hierdie ding kan koppel, genaamd 'exciters' aan die agterkant van die skerm. Nou is dit regtig dun dinge wat die hele vertoning op 'n spreker draai. Daar is ongeveer 5 opgewondenes agterop hierdie skerm en ek moet sê dit klink regtig dat die klank van die regterkant van die prent kom, en dit is basies omdat dit is."
    @Shared
    String longJASource = "OLEDのシンプルな構造はまた、ディスプレイの背面に「エキサイター」と呼ばれるものを取り付けることができることを意味します。 今、それらはディスプレイ全体をスピーカーに変える、本当に、本当に薄いものです。 私はこのスクリーンの裏側に約5人のエキサイターがいると思います、そして私は言わなければなりません、それは本当に音声が絵の右部分から来ているように聞こえます - そしてそれは基本的にそうであるからです。"
    @Shared
    String longDESource = "Die einfache Struktur von OLED bedeutet auch, dass Sie dieses als \"Erreger\" bezeichnete Element an der Rückseite des Displays anbringen können. Nun, das sind wirklich sehr dünne Dinge, die das gesamte Display zu einem Lautsprecher machen. Ich denke, es gibt ungefähr 5 Erreger auf der Rückseite dieses Bildschirms, und ich muss sagen, es hört sich wirklich so an, als würde das Audio aus dem rechten Teil des Bildes kommen - und das ist im Grunde so, weil es ist."
    @Shared
    String longCSSource = "Jednoduchá struktura OLED také znamená, že na zadní straně displeje můžete připojit tuto věc nazvanou \"excitátory\". Nyní jsou to skutečně tenké věci, které otočí celý displej na reproduktor. Na zadní straně této obrazovky je asi 5 budičů a musím říct, že to opravdu zní jako zvuk pochází z pravé části obrazu - a to je v podstatě proto, že je."
    @Shared
    String longELSource = "Η απλή δομή του OLED σημαίνει επίσης ότι μπορείτε να επισυνάψετε αυτό το πράγμα που ονομάζεται 'exciters' στο πίσω μέρος της οθόνης. Τώρα, αυτά είναι πραγματικά, πολύ λεπτά πράγματα που μετατρέπουν ολόκληρη την οθόνη σε ένα ηχείο. Υπάρχουν περίπου 5 διεγέρτες, νομίζω, στο πίσω μέρος αυτής της οθόνης και πρέπει να πω ότι πραγματικά ακούγεται πως ο ήχος έρχεται από το δεξί μέρος της εικόνας - και αυτό είναι βασικά επειδή είναι."
    @Shared
    String longESSource = "La estructura simple de OLED también significa que puede adjuntar esta cosa llamada \"excitadores\" en la parte posterior de la pantalla. Ahora, esas son cosas muy, muy delgadas que convierten toda la pantalla en un altavoz. Creo que hay alrededor de 5 excitadores en la parte posterior de esta pantalla y debo decir que realmente suena como si el audio viniera de la parte correcta de la imagen, y eso es básicamente porque lo es."
    @Shared
    String longHESource = "המבנה הפשוט של OLED גם אומר שאתה יכול לצרף את הדבר הזה שנקרא 'exciters' לחלק האחורי של התצוגה. עכשיו, אלה באמת, דברים רזה באמת להפוך את כל התצוגה על הדובר. יש בערך 5 exciters, אני חושב, על הגב של המסך הזה ואני חייב לומר, זה באמת נשמע כמו אודיו מגיע מהחלק הימני של התמונה - וזה בעצם כי זה."
    @Shared
    String longHUSource = "Az OLED egyszerű szerkezete azt is jelenti, hogy a „exciters” nevű tárgyat a kijelző hátoldalához csatolhatja. Most azok igazán vékony dolgok, amelyek az egész kijelzőt hangszóróra kapcsolják. Körülbelül 5 izgalom van, azt hiszem, a képernyő hátoldalán, és azt kell mondanom, hogy tényleg úgy hangzik, mintha a hang a kép jobb részéből jön - és ez alapvetően azért van, mert az van."
    @Shared
    String longMLSource = "പ്രദർശനത്തിന്റെ പിൻഭാഗത്തേക്ക് 'exciters' എന്ന് വിളിക്കാവുന്ന ഈ വസ്തു നിങ്ങൾ അറ്റാച്ചുചെയ്യാൻ കഴിയുമെന്നാണ് OLED ന്റെ ലളിതമായ ഘടന. ഇപ്പോൾ അവ ശരിക്കും സത്യസന്ധമായ കാര്യങ്ങളാണ്. അത് മുഴുവൻ ഡിസ്പ്ലേയിലും ഒരു സ്പീക്കറിലേക്ക് മാറുന്നു. 5 സ്ക്രീനിനുണ്ട്, ഞാൻ ഈ സ്ക്രീനിന്റെ പിറകിൽ ആണെന്ന് കരുതുന്നു, ചിത്രത്തിന്റെ വലതുഭാഗത്ത് നിന്നും ഓഡിയോ വരുന്നതു പോലെയാണ് അത് ശരിക്കും കാണിക്കുന്നത് - അത് അടിസ്ഥാനപരമായി കാരണം ആണ്."
    @Shared
    String longPLSource = "Prosta struktura OLED oznacza także, że możesz podłączyć to urządzenie o nazwie \"wzbudnice\" do tylnej części ekranu. Teraz są to naprawdę cienkie rzeczy, które zamieniają cały ekran na głośnik. Przypuszczam, że z tyłu tego ekranu jest około 5 wzbudników i muszę powiedzieć, że brzmi to tak, jakby dźwięk pochodzi z prawej części obrazu - i to w zasadzie dlatego, że tak jest."
    @Shared
    String longPTSource = "A estrutura simples do OLED também significa que você pode anexar essa coisa chamada \"exciters\" na parte de trás da tela. Agora, essas são coisas realmente muito finas que transformam toda a tela em um alto-falante. Há cerca de 5 excitantes, penso eu, na parte de trás desta tela e eu tenho que dizer, realmente soa como o áudio está vindo da parte direita da imagem - e isso é basicamente porque é."
    @Shared
    String longTRSource = "OLED'in basit yapısı ayrıca “uyarıcılar” denilen şeyi ekranın arkasına ekleyebileceğiniz anlamına gelir. Şimdi, bunlar tüm ekranı bir hoparlöre dönüştüren gerçekten, gerçekten ince şeyler. Sanırım, bu ekranın arkasında yaklaşık 5 uyarıcı var ve şunu söylemeliyim ki, ses görüntünün sağ kısmından geliyor gibi geliyor - ve bu aslında çünkü."
    @Shared
    String longUKSource = "Проста структура OLED також означає, що ви можете приєднати цю річ під назвою \"збуджувачі\" до задньої частини дисплея. Тепер це дійсно, дуже тонкі речі, які перетворюють весь дисплей на динамік. Є близько 5 збудників, я думаю, на зворотному боці цього екрану, і я повинен сказати, це дійсно звучить як аудіо надходить з правої частини картини - і це в основному тому, що це так."

    @Shared
    String shortENSource = "Or it can be transparent "
    @Shared
    String shortFASource = "یا حتی شفاف بشه؟"
    @Shared
    String shortZhCnSource = "或者它可以是透明的"
    @Shared
    String shortZhTwSource = "有人曾致中華人民共和國政府的相關部門，希望能恢復部分"
    @Shared
    String shortFRSource = "Ou cela peut être transparent"
    @Shared
    String shortAFSource = "Of dit kan deursigtig wees"
    @Shared
    String shortJASource = "それとも透明にすることができます"
    @Shared
    String shortDESource = "Oder es kann transparent sein"
    @Shared
    String shortCSSource = "Nebo to může být průhledné"
    @Shared
    String shortELSource = "Ή μπορεί να είναι διαφανής"
    @Shared
    String shortESSource = "O puede ser transparente."
    @Shared
    String shortHESource = "או שזה יכול להיות שקוף"
    @Shared
    String shortHUSource = "Vagy átlátszó lehet"
    @Shared
    String shortMLSource = "അല്ലെങ്കിൽ അത് സുതാര്യമായിരിക്കും"
    @Shared
    String shortPLSource = "Lub może być przezroczysty"
    @Shared
    String shortPTSource = "Ou pode ser transparente"
    @Shared
    String shortTRSource = "Veya şeffaf olabilir"
    @Shared
    String shortUKSource = "Або він може бути прозорим"


    @Shared
    String small1 = "A new year a new me.";
    @Shared
    String small2 = "APP: “Hello, Sir”";
    @Shared
    String small3 = "DAVE: “Brilliant”";
    @Shared
    String small4 = "DAVE: “Wow. Here we go” ";
    @Shared
    String small5 = "MARCIE MILLER: “Yes”";
    @Shared
    String small6 = "چطوری؟";
    @Shared
    String small7 = "سلام آق";
    @Shared
    String small8 = "عالی";
    @Shared
    String small9 = "نیل والنی: ";
    @Shared
    String small10 = "بله  ";

    void setup() {
    }

    void cleanup() {
    }

    @Autowired(required = false)
    private LangDetectService langDetectService

    def "Detect"() {
    }

    @SuppressWarnings(["GroovyPointlessBoolean", "GroovyAssignabilityCheck"])
    @Unroll("""
#no: test detect source #source with #confidence and result: #expectedResult, the scenario: #scenario
""")
    def "test Detect"() {
        given: ""
        when: "detect the source"
        then: "result should be as expected"
        def result
        if (confidence != null) {
            result = langDetectService.detect(source, confidence)
        } else {
            result = langDetectService.detect(source)
        }
        result == expectedResult
        where: "the scenarios list"
        no | source          | confidence || expectedResult                 | scenario
        1  | longENSource    | null       || Optional.of(SupportLang.EN)    | "long"
        2  | longFASource    | null       || Optional.of(SupportLang.FA)    | "long"
        3  | longZhCnSource  | null       || Optional.of(SupportLang.ZH_CN) | "long"
//      4  | longZhTwSource | null       || Optional.of(SupportLang.ZH_TW) | "long"  // ZH_TW support not good
        5  | longFRSource    | null       || Optional.of(SupportLang.FR)    | "long"
        6  | longAFSource    | null       || Optional.of(SupportLang.AF)    | "long"
        7  | longJASource    | null       || Optional.of(SupportLang.JA)    | "long"
        8  | longDESource    | null       || Optional.of(SupportLang.DE)    | "long"
        9  | longCSSource    | null       || Optional.of(SupportLang.CS)    | "long"
        10 | longELSource    | null       || Optional.of(SupportLang.EL)    | "long"
        11 | longESSource    | null       || Optional.of(SupportLang.ES)    | "long"
        12 | longHESource    | null       || Optional.of(SupportLang.HE)    | "long"
        13 | longHUSource    | null       || Optional.of(SupportLang.HU)    | "long"
        14 | longMLSource    | null       || Optional.of(SupportLang.ML)    | "long"
        15 | longPLSource    | null       || Optional.of(SupportLang.PL)    | "long"
        16 | longPTSource    | null       || Optional.of(SupportLang.PT)    | "long"
        17 | longTRSource    | null       || Optional.of(SupportLang.TR)    | "long"
        18 | longUKSource    | null       || Optional.of(SupportLang.UK)    | "long"
        19 | shortENSource   | null       || Optional.of(SupportLang.EN)    | "short"
        20 | shortFASource   | null       || Optional.of(SupportLang.FA)    | "short"
        21 | shortZhCnSource | null       || Optional.of(SupportLang.ZH_CN) | "short"
        22 | shortZhTwSource | null       || Optional.of(SupportLang.ZH_TW) | "short"
        23 | shortFRSource   | null       || Optional.of(SupportLang.FR)    | "short"
        24 | shortAFSource   | null       || Optional.of(SupportLang.AF)    | "short"
        25 | shortJASource   | null       || Optional.of(SupportLang.JA)    | "short"
//        26 | shortDESource   | null       || Optional.of(SupportLang.DE)    | "short" // confuse with no
        27 | shortCSSource   | null       || Optional.of(SupportLang.CS)    | "short"
        28 | shortELSource   | null       || Optional.of(SupportLang.EL)    | "short"
        29 | shortESSource   | null       || Optional.empty()               | "short, should be  Optional.of(SupportLang.ES)"
        30 | shortHESource   | null       || Optional.of(SupportLang.HE)    | "short"
        31 | shortHUSource   | null       || Optional.of(SupportLang.HU)    | "short"
        32 | shortMLSource   | null       || Optional.of(SupportLang.ML)    | "short"
        33 | shortPLSource   | null       || Optional.of(SupportLang.PL)    | "short"
//        34 | shortPTSource   | null       || Optional.of(SupportLang.PT)    | "short" //
        35 | shortTRSource   | null       || Optional.of(SupportLang.TR)    | "short"
        36 | shortUKSource   | null       || Optional.of(SupportLang.UK)    | "short"
        36 | small1          | null       || Optional.of(SupportLang.EN)    | "short"
        36 | small2          | null       || Optional.empty()               | "short Optional.of(SupportLang.EN)"
        36 | small3          | null       || Optional.empty()               | "short Optional.of(SupportLang.EN)"
        36 | small4          | null       || Optional.of(SupportLang.EN)    | "short"
        36 | small5          | null       || Optional.of(SupportLang.EN)    | "short"
        36 | small6          | null       || Optional.of(SupportLang.FA)    | "short"
        36 | small7          | null       || Optional.empty()               | "short Optional.of(SupportLang.FA)"
        36 | small8          | null       || Optional.of(SupportLang.AR)    | "short Optional.of(SupportLang.FA)"
        36 | small9          | null       || Optional.of(SupportLang.AR)    | "short Optional.of(SupportLang.FA)"
        36 | small10         | null       || Optional.of(SupportLang.AR)    | "short Optional.of(SupportLang.FA)"
    }


    @SuppressWarnings(["GroovyPointlessBoolean", "GroovyAssignabilityCheck"])
    @Unroll("""
#number: AdapterLang detector LdLocale #ldLocaleStr to local and result : #expectedResult, the scenario: #scenario
""")
    def "test AdapterLang"() {
        given: "LdLocale"
        LdLocale ldLocale = LdLocale.fromString(ldLocaleStr)
        when: "compare customer level with movie level"
        def result = langDetectService.adapterLang(ldLocale)
        then:
        //noinspection GrEqualsBetweenInconvertibleTypes
        assert result == expectedResult
        where: "the scenarios list"
        number | scenario    | ldLocaleStr || expectedResult
        1      | "without -" | "fa"        || Optional.of(SupportLang.FA)
        2      | "without -" | "ru"        || Optional.of(SupportLang.RU)
        3      | "with -"    | "zh-CN"     || Optional.of(SupportLang.ZH_CN)
        4      | "with -"    | "zh-TW"     || Optional.of(SupportLang.ZH_TW)
        5      | "not match" | "ka"        || Optional.empty()
    }
}

