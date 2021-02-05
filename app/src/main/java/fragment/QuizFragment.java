package fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myspace.MainActivity;
import com.example.myspace.R;

import services.DatabasHelper;


public class QuizFragment extends Fragment {
    TextView question;
    RadioGroup answersGrp;
    RadioButton a1, a2, a3, a4;
    Button btnNext;
    ImageView planetImgs;
    DatabasHelper databasHelper;

    int[] images= {
            R.drawable.earth,
            R.drawable.solarsystem,
            R.drawable.planets,
            R.drawable.solarsystem,
            R.drawable.sun,
            R.drawable.mars,
            R.drawable.galaxy,
            R.drawable.molecule,
            R.drawable.mercury,
            R.drawable.sun

    };
    /*String questions[] = {
            "quelle est la forme de la terre?",
            "quel est le centre du système solaire?",
            "Laquelle d'entre elles n'est pas une planète géante?",
            "quel âge a le système solaire?",
            "Quelle est la nature du soleil?",
            "La planète Mars se situe entre?",
            "À quelle galaxie appartient notre système solaire?",
            "Quelles sont les deux principales compositions chimiques du soleil?",
            "Quelle est la plus petite planète de notre système solaire?",
            "À quelle distance la terre est-elle du soleil?"
    };*/
    /*String answers[]= {
            "sphère","Le soleil","Terre","4,571 milliards d'années", "étoile","Jupiter et la terre",
            "Voie lactée","l'hydrogène et l'hélium", "Mercure","93 millions de miles"
    };*/
   /* String options[]={
            "disque", "sphère", "rectangle", "chaîne", //1
            "La terre", "Jésus", "Le soleil", "La lune", //2
            "Jupiter","Saturne","Terre"  ,"Neptune",//3
            "1442 ans", "2021 ans","13,77 milliards d'années","4,571 milliards d'années", //4
            "planète"," comète","étoile"," lune", //5
            "Soleil et Mercure" , "Jupiter et la Terre","Uranus et Neptune","Aucun", //6
            "Galaxie d'Andromède" ,"Voie lactée","Galaxie-comète","NGC 4567/8",//7
            "l'hydrogène et l'hélium","l'oxygène et le carbone"," l'hélium et l'azote","l'oxygène et l'hydrogène", //8
            "La Terre","Mars", "Mercure","Vénus", //9
            "93 millions de miles","180 millions de miles","10 millions de miles","63 millions de miles" //10

    };*/
    int incrementQuestion = 0;
    int score = 0;
    private ActionBar getActionBar() {
        return ((AppCompatActivity) getActivity()).getSupportActionBar();
    }


    Bundle bundle = new Bundle();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);

        question = (TextView) view.findViewById(R.id.question);
        answersGrp = (RadioGroup) view.findViewById(R.id.answersGroup);
        a1=(RadioButton) view.findViewById(R.id.answerOne);
        a2=(RadioButton) view.findViewById(R.id.answerTwo);
        a3=(RadioButton) view.findViewById(R.id.answerThree);
        a4=(RadioButton) view.findViewById(R.id.answerFour);
        btnNext = (Button) view.findViewById(R.id.btnNext);
        planetImgs = (ImageView) view.findViewById(R.id.planet_img);

        String []questions = getContext().getResources().getStringArray(R.array.questionss);
        String []answers = getContext().getResources().getStringArray(R.array.answers);
        String []options = getContext().getResources().getStringArray(R.array.options);

        databasHelper = new DatabasHelper(getContext());

        question.setText(questions[incrementQuestion]);
        a1.setText(options[0]);
        a2.setText(options[1]);
        a3.setText(options[2]);
        a4.setText(options[3]);
        planetImgs.setImageResource(images[0]);

        //submit answer and go to next question
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(answersGrp.getCheckedRadioButtonId()==-1)
                {
                    Toast.makeText(getContext(), "Choisir une réponse", Toast.LENGTH_SHORT).show();
                    return;
                }
                RadioButton userAnswer = (RadioButton) answersGrp.findViewById(answersGrp.getCheckedRadioButtonId());
                String answerText = userAnswer.getText().toString();
//                Toast.makeText(getContext(),answerText,Toast.LENGTH_SHORT).show();
                if(answerText.equals(answers[incrementQuestion])){
                    score ++;
                    Toast.makeText(getContext(),"Tout à fait ! ", Toast.LENGTH_SHORT).show();
                }
                else{
                    //score --;
                    Toast.makeText(getContext(),"C'est faux ", Toast.LENGTH_SHORT).show();
                }
                incrementQuestion ++;
                if(incrementQuestion<questions.length)
                {
                    question.setText(questions[incrementQuestion]);
                    a1.setText(options[incrementQuestion*4]);
                    a2.setText(options[incrementQuestion*4 +1]);
                    a3.setText(options[incrementQuestion*4 +2]);
                    a4.setText(options[incrementQuestion*4 +3]);
                    planetImgs.setImageResource(images[incrementQuestion]);

                }else if(incrementQuestion == questions.length){   // not working
                    ProfileFragment profilefragment = new ProfileFragment();
                    databasHelper.updateScores(score,getActivity().getIntent().getStringExtra("Login"));
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.mainLayout,profilefragment);
                    //fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();

                }
                answersGrp.clearCheck();
            }
        });

        return view;
    }

}