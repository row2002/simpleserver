package ru.simpleserver;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;

import java.util.HashSet;
import java.util.Set;

public class HomePage extends WebPage {
  private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
  private static final String PREFIX = "1d";
  private static Set<String> passwords;

  private String login;
  private String password;

  static {
    passwords = new HashSet<String>();

    for (int i=0; i<250; i++) {
      passwords.add(PREFIX + getRandomString(3));
    }
  }

  public HomePage() {
    Form form = new Form("form");
    form.setDefaultModel(new CompoundPropertyModel<HomePage>(this));
    add(form);

    add(new FeedbackPanel("feedback"));
    form.add(new RequiredTextField<String>("login").setOutputMarkupId(true));
    form.add(new PasswordTextField("password").setOutputMarkupId(true));
    form.add(new Button("submit"){
      @Override
      public void onSubmit() {
        if (!login.equals(password) || !passwords.contains(password)) {
          error("invalid username or password");
          try {
            Thread.sleep(10);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          return;
        }

        success("You get in!");
      }
    });
  }

  public static String getRandomString(int length) {
    StringBuilder sb = new StringBuilder();

    for (int i=0; i<length; i++) {
      int rnd = (int) Math.round(Math.random() * (ALPHABET.length() - 1));
      sb.append(ALPHABET.charAt(rnd));
    }

    return sb.toString();
  }
}
