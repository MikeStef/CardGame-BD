package com.micste.busdriver;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    private Handler handler = new Handler();
    private Animation imageHighlight;
    private MediaPlayer mediaPlayer;

    //Views
    private ImageView cardImageView1;
    private ImageView cardImageView2;
    private ImageView cardImageView3;
    private ImageView cardImageView4;
    private ImageView cardImageView5;
    private Button btn1;
    private Button btn2;
    private TextView gameText;
    private TextView deckCounter;

    private String colorGuess;
    private String valueGuess;
    private String playerName;
    private ArrayList<Integer> lastCard = new ArrayList<>();
    private Deck deck;
    private Card card;
    private Boolean isFirstTurn = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        deck = new Deck();
        deck.shuffleCards();

        //Definera views
        btn1 = (Button) findViewById(R.id.gamebutton_1);
        btn2 = (Button) findViewById(R.id.gamebutton_2);
        gameText = (TextView) findViewById(R.id.text_gamehint);
        deckCounter = (TextView) findViewById(R.id.number_card_count);
        cardImageView1 = (ImageView) findViewById(R.id.card_1);
        cardImageView2 = (ImageView) findViewById(R.id.card_2);
        cardImageView3 = (ImageView) findViewById(R.id.card_3);
        cardImageView4 = (ImageView) findViewById(R.id.card_4);
        cardImageView5 = (ImageView) findViewById(R.id.card_5);

        deckCounter.setText(deck.getSize());
        mediaPlayer = MediaPlayer.create(this, R.raw.effect_flipcard);

        imageHighlight = AnimationUtils.loadAnimation(this, R.anim.image_highlight);

        showSetNameDialog();

    }

    public void gameInput(View v) {

        gameInputPause();

        if (!deck.isEmpty()) {

            /*
            för att testa spelet lättare används denna metod istället för randomCard()
            card = deck.getCardTest();
            deck.increaseTestInt();
            */

            card = deck.randomCard();
            cardFlip(getCardView(), true);
            mediaPlayer.start();
            getCardView().setTag(card.getValue());
            deckCounter.setText(deck.getSize());

            if (isFirstTurn) {
                switch (v.getId()) {
                    case R.id.gamebutton_1:
                        colorGuess = btn1.getText().toString();
                        break;
                    case R.id.gamebutton_2:
                        colorGuess = btn2.getText().toString();
                        break;
                }
                //Kolla om färg gissning = kortet
                if (colorGuess.equals(card.getColor())) {
                    gameText.setText(getResources().getString(R.string.correct));
                    isFirstTurn = false;
                    lastCard.add(card.getValue());

                    if (getCardView() == null) {

                    }
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //delay ändring av text
                            //knapparna justerat till nästa fråga
                            gameText.setText(getResources().getString(R.string.question_value));
                            btn1.setText(getResources().getString(R.string.btn_higher));
                            btn2.setText(getResources().getString(R.string.btn_lower));
                            highlightCard();

                        }
                    }, 2000);

                } else {
                    gameText.setText(getResources().getString(R.string.incorrect));
                    resetGame();
                }
            } else { //Logik som körs om !isFirstTurn
                switch (v.getId()) {
                    case R.id.gamebutton_1:
                        valueGuess = btn1.getText().toString();
                        break;
                    case R.id.gamebutton_2:
                        valueGuess = btn2.getText().toString();
                        break;
                }
                //Jämför kortet med det förra om värdet är högre och knapp högre var tryckt
                if (card.getValue() > lastCard.get(lastCard.size() - 1) && valueGuess.equals("Higher")) {
                    gameText.setText(getResources().getString(R.string.value_is_higher));
                    lastCard.add(card.getValue());

                    if (getCardView() == null) {
                        gameWon();
                    } else {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //delay ändring av text
                                //knapparna justerat till nästa fråga
                                gameText.setText(getResources().getString(R.string.question_value));
                                btn1.setText(getResources().getString(R.string.btn_higher));
                                btn2.setText(getResources().getString(R.string.btn_lower));
                                highlightCard();

                            }
                        }, 2000);
                    }

                    //Jämför kortet med det förra om värdet är lägre och knapp lägre var tryckt
                } else if (card.getValue() < lastCard.get(lastCard.size() - 1) && valueGuess.equals("Lower")) {
                    gameText.setText(getResources().getString(R.string.value_is_lower));
                    lastCard.add(card.getValue());

                    if (getCardView() == null) {
                        gameWon();
                    } else {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //delay ändring av text
                                //knapparna justerat till nästa fråga
                                gameText.setText(getResources().getString(R.string.question_value));
                                btn1.setText(getResources().getString(R.string.btn_higher));
                                btn2.setText(getResources().getString(R.string.btn_lower));
                                highlightCard();

                            }
                        }, 2000);
                    }

                    //Kolla jämnt värde
                } else if (card.getValue() == lastCard.get(lastCard.size() - 1)) {
                    gameText.setText(getResources().getString(R.string.value_is_equal));
                    resetGame();
                } else {
                    gameText.setText(getResources().getString(R.string.incorrect));
                    resetGame();
                }

            }
        } else {
            showDeckEmptyDialog();
        }


    }

    /*
    Returnerar rätt ImageView som ska användas under spelets gång.
    null = spelaren har vunnit, alla kort är vända.
     */
    public ImageView getCardView() {

        ImageView imgView = null;

        if (cardImageView5.getTag().equals("CardBack")) {
            imgView = cardImageView5;
        } else if (cardImageView4.getTag().equals("CardBack")) {
            imgView = cardImageView4;
        } else if (cardImageView3.getTag().equals("CardBack")) {
            imgView = cardImageView3;
        } else if (cardImageView2.getTag().equals("CardBack")) {
            imgView = cardImageView2;
        } else if (cardImageView1.getTag().equals("CardBack")) {
            imgView = cardImageView1;
        }

        return imgView;
    }

    /*
    Startar om spelet med en 2s delay via handler, behåller mängden kort i deck.
    Flippar tillbaka alla kort som är vända.
     */
    public void resetGame() {

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (!cardImageView5.getTag().equals("CardBack")) {
                    cardFlip(cardImageView5, false);
                    cardImageView5.setTag("CardBack");
                }
                if (!cardImageView4.getTag().equals("CardBack")) {
                    cardFlip(cardImageView4, false);
                    cardImageView4.setTag("CardBack");
                }
                if (!cardImageView3.getTag().equals("CardBack")) {
                    cardFlip(cardImageView3, false);
                    cardImageView3.setTag("CardBack");
                }
                if (!cardImageView2.getTag().equals("CardBack")) {
                    cardFlip(cardImageView2, false);
                    cardImageView2.setTag("CardBack");
                }
                if (!cardImageView1.getTag().equals("CardBack")) {
                    cardFlip(cardImageView1, false);
                    cardImageView1.setTag("CardBack");
                }

                btn1.setText(getResources().getString(R.string.btn_red));
                btn2.setText(getResources().getString(R.string.btn_black));
                gameText.setText(getResources().getString(R.string.question_color));

                isFirstTurn = true;

                highlightCard();

            }
        }, 2000);
        deckCounter.setText(deck.getSize());

    }

    public void gameWon() {

        if (getCardView() == null) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    showWinDialog();
                }
            }, 1000);

        }

    }

    public void cardFlip(final ImageView imageView, final Boolean isBackShown) {

        final Drawable cardFront = ContextCompat.getDrawable(this, card.getImage());
        final Drawable cardBack = ContextCompat.getDrawable(this, R.drawable.card_back);
        final Drawable cardBackHighlighted = ContextCompat.getDrawable(this, R.drawable.card_back_qmark);
        imageView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        imageView.setRotationY(0f);
        imageView.animate().rotationY(90f).setDuration(150).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (isBackShown) {
                    imageView.setImageDrawable(cardFront);
                    imageView.setRotationY(270f);
                    imageView.animate().rotationY(360f).setDuration(150).setListener(null);
                    imageView.clearAnimation();
                } else {

                    if (imageView == cardImageView5) {
                        imageView.setImageDrawable(cardBackHighlighted);
                    } else {
                        imageView.setImageDrawable(cardBack);
                    }
                    imageView.setRotationY(270f);
                    imageView.animate().rotationY(360f).setDuration(150).setListener(null);
                }
                imageView.setLayerType(View.LAYER_TYPE_NONE, null);
            }
        });

    }

    /*
    Hindrar gameInput i 2 sek under tiden då kort flippas/spel text ändras.
     */
    public void gameInputPause() {

        btn1.setEnabled(false);
        btn2.setEnabled(false);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                btn1.setEnabled(true);
                btn2.setEnabled(true);
            }
        }, 2000);

    }

    public void highlightCard() {

        if (getCardView() != null) {

            getCardView().setImageResource(R.drawable.card_back_qmark);
            getCardView().startAnimation(imageHighlight);
        }

    }

    public void showWinDialog() {

        DatabaseHandler dbHandler = new DatabaseHandler();

        dbHandler.writeNewHighscore(playerName, deck.getCount());

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog winDialog = builder.create();
        LayoutInflater inflater = getLayoutInflater();
        View dialogWinLayout = inflater.inflate(R.layout.dialog_win, null);

        winDialog.setView(dialogWinLayout);
        winDialog.setCancelable(false);

        Button btn_ok = (Button) dialogWinLayout.findViewById(R.id.btn_ok);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                winDialog.cancel();
                deck = new Deck();
                resetGame();

            }
        });

        TextView tvWinCount = (TextView) dialogWinLayout.findViewById(R.id.text_win_count);
        tvWinCount.setText("It took you " + deck.getCount() + " tries!");

        ImageView iv_star1 = (ImageView) dialogWinLayout.findViewById(R.id.star1);
        ImageView iv_star2 = (ImageView) dialogWinLayout.findViewById(R.id.star2);
        ImageView iv_star3 = (ImageView) dialogWinLayout.findViewById(R.id.star3);

        if (deck.getCount() < 15) {
            iv_star1.setImageResource(R.drawable.icon_star);
            iv_star2.setImageResource(R.drawable.icon_star);
            iv_star3.setImageResource(R.drawable.icon_star);
        } else if (deck.getCount() < 35) {
            iv_star1.setImageResource(R.drawable.icon_star);
            iv_star2.setImageResource(R.drawable.icon_star);
        } else {
            iv_star1.setImageResource(R.drawable.icon_star);
        }

        winDialog.show();

    }

    public void showDeckEmptyDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog deckEmptyDialog = builder.create();
        LayoutInflater inflater = getLayoutInflater();
        View dialogEmptyDeckLayout = inflater.inflate(R.layout.dialog_deck_empty, null);

        deckEmptyDialog.setView(dialogEmptyDeckLayout);
        deckEmptyDialog.setCancelable(false);

        Button btn_ok = (Button) dialogEmptyDeckLayout.findViewById(R.id.dialogEmpty_btn_ok);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deckEmptyDialog.cancel();
                deck = new Deck();
                resetGame();

            }
        });

        deckEmptyDialog.show();


    }

    public void showSetNameDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog setNameDialog = builder.create();
        LayoutInflater inflater = getLayoutInflater();
        View dialogSetNameLayout = inflater.inflate(R.layout.dialog_set_name, null);

        setNameDialog.setView(dialogSetNameLayout);
        setNameDialog.setCancelable(false);

        final EditText et_setName = (EditText) dialogSetNameLayout.findViewById(R.id.et_name);
        Button btn_ok = (Button) dialogSetNameLayout.findViewById(R.id.dialogSetName_btn_ok);

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!et_setName.getText().toString().isEmpty()) {
                    playerName = et_setName.getText().toString();
                    setNameDialog.cancel();
                    highlightCard();
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "You need to enter your name/nickname", Toast.LENGTH_SHORT);
                    toast.show();
                }

            }
        });

        setNameDialog.show();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.release();
    }


}
