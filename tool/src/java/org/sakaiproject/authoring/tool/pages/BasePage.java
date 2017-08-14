package org.sakaiproject.authoring.tool.pages;

import org.apache.log4j.Logger;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnLoadHeaderItem;
import org.apache.wicket.markup.head.StringHeaderItem;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.sakaiproject.authoring.logic.LessonBuilderProxy;
import org.sakaiproject.authoring.logic.ProjectLogic;
import org.sakaiproject.authoring.logic.SakaiProxy;
import org.sakaiproject.authoring.logic.SamigoProxy;
import org.sakaiproject.exception.IdUnusedException;
import org.sakaiproject.util.ResourceLoader;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * This is our base page for our Sakai app. It sets up the containing markup and
 * top navigation. All top level pages should extend from this page so as to
 * keep the same navigation. The content for those pages will be rendered in the
 * main area below the top nav.
 * <p>
 * <p>
 * It also allows us to setup the API injection and any other common methods,
 * which are then made available in the other pages.
 *
 * @author Steve Swinsburg (steve.swinsburg@gmail.com)
 */
public class BasePage extends WebPage implements IHeaderContributor {

    private static final Logger log = Logger.getLogger(BasePage.class);

    @SpringBean(name = "org.sakaiproject.authoring.logic.SakaiProxy")
    protected static SakaiProxy sakaiProxy;

    @SpringBean(name = "org.sakaiproject.authoring.logic.ProjectLogic")
    protected static ProjectLogic projectLogic;

    @SpringBean(name = "org.sakaiproject.authoring.logic.LessonBuilderProxy")
    protected static LessonBuilderProxy lessonProxy;

    @SpringBean(name = "org.sakaiproject.authoring.logic.SamigoProxy")
    protected static SamigoProxy samProxy;

    Link<Void> firstLink;
    Link<Void> secondLink;
    Link<Void> thirdLink;
    Link<Void> fourthLink;
    Link<Void> fifthLink;

    FeedbackPanel feedbackPanel;

    public BasePage() {

        log.debug("BasePage()");

        // first link
        firstLink = new Link<Void>("firstLink") {
            private static final long serialVersionUID = 1L;

            public void onClick() {

                setResponsePage(new ActivityPage());
            }
        };
        firstLink.add(new Label("firstLinkLabel", new ResourceModel("link.first")).setRenderBodyOnly(true));
        firstLink.add(new AttributeModifier("title", true, new ResourceModel("link.first.tooltip")));
        add(firstLink);

        // second link
        secondLink = new Link<Void>("secondLink") {
            private static final long serialVersionUID = 1L;

            public void onClick() {
                setResponsePage(new AssessmentPage());
            }
        };
        secondLink.add(new Label("secondLinkLabel", new ResourceModel("link.second")).setRenderBodyOnly(true));
        secondLink.add(new AttributeModifier("title", true, new ResourceModel("link.second.tooltip")));
        add(secondLink);

        // third link
        thirdLink = new Link<Void>("thirdLink") {
            private static final long serialVersionUID = 1L;

            public void onClick() {
                setResponsePage(new MetadataPage());
            }
        };
        thirdLink.add(new Label("thirdLinkLabel", new ResourceModel("link.third")).setRenderBodyOnly(true));
        thirdLink.add(new AttributeModifier("title", true, new ResourceModel("link.third.tooltip")));
        add(thirdLink);

        // fourth link
        fourthLink = new Link<Void>("fourthLink") {
            private static final long serialVersionUID = 1L;

            public void onClick() {
                setResponsePage(new GradeThresholdPage());
            }
        };
        fourthLink.add(new Label("fourthLinkLabel", new ResourceModel("link.fourth")).setRenderBodyOnly(true));
        fourthLink.add(new AttributeModifier("title", true, new ResourceModel("link.fourth.tooltip")));
        add(fourthLink);

        // fifth link
        fifthLink = new Link<Void>("fifthLink") {
            private static final long serialVersionUID = 1L;

            public void onClick() {
                setResponsePage(new FeedbackPage());
            }
        };
        fifthLink.add(new Label("fifthLinkLabel", new ResourceModel("link.fifth")).setRenderBodyOnly(true));
        fifthLink.add(new AttributeModifier("title", true, new ResourceModel("link.fifth.tooltip")));
        add(fifthLink);

        // Add a FeedbackPanel for displaying our messages
        feedbackPanel = new FeedbackPanel("feedback") {

            @Override
            protected Component newMessageDisplayComponent(final String id, final FeedbackMessage message) {
                final Component newMessageDisplayComponent = super.newMessageDisplayComponent(id, message);

                if (message.getLevel() == FeedbackMessage.ERROR || message.getLevel() == FeedbackMessage.DEBUG
                        || message.getLevel() == FeedbackMessage.FATAL
                        || message.getLevel() == FeedbackMessage.WARNING) {
                    add(AttributeModifier.replace("class", "alertMessage"));
                } else if (message.getLevel() == FeedbackMessage.INFO) {
                    add(AttributeModifier.replace("class", "success"));
                }

                return newMessageDisplayComponent;
            }
        };
        add(feedbackPanel);

        setUserPreferredLocale();
    }

    /**
     * Retrieve the site language for activity and assessment identification
     */
    protected static String retrieveSiteLanguage() {
        String language = "";
        try {
            language = sakaiProxy.getCurrentSite().getProperties().getProperty("language");
            switch (language) {
                case "english":
                    language = "EN";
                    break;
                case "german":
                    language = "DE";
                    break;
                case "spanish":
                    language = "ES";
                    break;
                case "DAF":
                    language = "DAF";
                    break;
                case "DAF1617":
                    language = "DAF1617";
                    break;
                default:
                    throw new IllegalArgumentException("Invalid language: " + language);
            }
        } catch (IdUnusedException e) {
            e.printStackTrace();
        }
        return language;
    }

    /**
     * Helper to clear the feedbackpanel display.
     *
     * @param f FeedBackPanel
     */
    public void clearFeedback(FeedbackPanel f) {
        if (!f.hasFeedbackMessage()) {
            f.add(AttributeModifier.replace("class", ""));
        }
    }

    /**
     * Allow overrides of the user's locale
     */
    public void setUserPreferredLocale() {
        ResourceLoader rl = new ResourceLoader();
        Locale locale = rl.getLocale();
        log.debug("User preferred locale: " + locale);
        getSession().setLocale(locale);
    }

    /**
     * This block adds the required wrapper markup to style it like a Sakai
     * tool. Add to this any additional CSS or JS references that you need.
     */
    public void renderHead(IHeaderResponse response) {
        // get the Sakai skin header fragment from the request attribute
        HttpServletRequest request = (HttpServletRequest) getRequest().getContainerRequest();

        response.render(StringHeaderItem.forString((String) request.getAttribute("sakai.html.head")));
        response.render(OnLoadHeaderItem.forScript("setMainFrameHeight( window.name )"));

        // Tool additions (at end so we can override if required)
        response.render(StringHeaderItem
                .forString("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />"));
        // response.renderCSSReference("css/my_tool_styles.css");
        // response.renderJavascriptReference("js/my_tool_javascript.js");
    }

    /**
     * Helper to disable a link. Add the Sakai class 'current'.
     */
    protected void disableLink(Link<Void> l) {
        l.add(new AttributeAppender("class", new Model<String>("current"), " "));
        l.setEnabled(false);
    }

}
