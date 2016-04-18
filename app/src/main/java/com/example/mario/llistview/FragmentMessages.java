package com.example.mario.llistview;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentMessages.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentMessages#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentMessages extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    String mensaje="";
    String temp="";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FragmentMessages() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentMessages.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentMessages newInstance(String param1, String param2) {
        FragmentMessages fragment = new FragmentMessages();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final ArrayList<String> allmessages = new ArrayList<String>();
        final String[] values = new String[] { "Android List View",
                "Adapter implementation",
                "Simple List View In Android",
                "Create List View Android",
                "Android Example",
                "List View Source Code",
                "List View Array Adapter",
                "Android Example List View"
        };

        final List<UsersMessage> mensajes=new ArrayList<UsersMessage>();
        View v=inflater.inflate(R.layout.fragment_fragment_messages, container, false);
        final Activity a =getActivity();
        Intent i=a.getIntent();
        final UsersMessage uIds= (UsersMessage) i.getSerializableExtra("UsersIds");

        String primero = String.valueOf(uIds.getFrom());
        String segundo = String.valueOf(uIds.getTo());
        if (uIds.getFrom()>uIds.getTo())
        {
            primero=String.valueOf(uIds.getTo());
            segundo=String.valueOf(uIds.getFrom());
        }
        final String file = primero+segundo;
        final EditText mens= (EditText) v.findViewById(R.id.mensaje);

        final ListView lnmensajes=(ListView)v.findViewById(R.id.mensajesdesp);



        ImageButton bs= (ImageButton) v.findViewById(R.id.button_send);
        ImageButton br= (ImageButton) v.findViewById(R.id.recargar);
        bs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mensaje="From"+String.valueOf(uIds.getFrom())+":" + mens.getText().toString();
                uIds.setText(mensaje);
                new MyQueryTaskpost(uIds).execute();
                mensajes.add(uIds);


                allmessages.add(mensaje);

                FileOutputStream outputStream;

                try {
                    outputStream = a.openFileOutput(file, Context.MODE_PRIVATE);
                    outputStream.write(mensaje.getBytes());
                    outputStream.close();
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),R.layout.rowlayout, R.id.textlistview,allmessages);
                    lnmensajes.setAdapter(adapter);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        br.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    ArrayList<String> result =new MyQueryTaskMessages().execute("messages", String.valueOf(uIds.getTo()), String.valueOf(uIds.getFrom())).get();
                    for (String k:result) {
                        allmessages.add(k);
                    }

                    FileInputStream fin =a.openFileInput(file);
                    int c;
                    while( (c = fin.read()) != -1){

                        if ( (char)c!='\n'){
                            temp = temp + Character.toString((char)c);

                        }
                        if ((char)c=='\n'){
                            FileOutputStream outputStream = a.openFileOutput(file, Context.MODE_PRIVATE);
                            outputStream.write(temp.getBytes());
                            outputStream.close();
                            allmessages.add(temp);
                            temp="";
                        }

                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),R.layout.rowlayout, R.id.textlistview,allmessages);
                    lnmensajes.setAdapter(adapter);


//string temp contains all the data of the file.
                    fin.close();



                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
