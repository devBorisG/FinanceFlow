package finance.corp.financeflowinfrastructure.adapter.primary.response;

import finance.corp.financeflowutils.constant.Constants;
import finance.corp.financeflowutils.exception.messages.Message;
import finance.corp.financeflowutils.helper.ObjectHelper;
import lombok.Getter;

import static finance.corp.financeflowutils.helper.ObjectHelper.getDefaultIfNull;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Response<T> {
    private List<Message> messages;
    private List<T> data;
    private String token;

    public Response(){
        setMessages(new ArrayList<>());
        setData(new ArrayList<>());
        setToken(token);
    }

    public Response(List<Message> messages, List<T> data){
        super();
        setMessages(messages);
        setData(data);
        setToken(token);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = ObjectHelper.getDefaultIfNull(token, Constants.EMPTY_STRING);
    }

    public void setMessages(List<Message> messages) {
        this.messages = getDefaultIfNull(messages, new ArrayList<>());
    }

    public void setData(List<T> data) {
        this.data = getDefaultIfNull(data, new ArrayList<>());
    }

    public void addFatalMessage(final String content){
        getMessages().add(Message.createFatalMessage(content));
    }

    public void addErrorMessage(final String content){
        getMessages().add(Message.createErrorMessage(content));
    }

    public void addWarningMessage(final String content){
        getMessages().add(Message.createWarningMessage(content));
    }

    public void addInfoMessage(final String content){
        getMessages().add(Message.createInfoMessage(content));
    }

    public void addSuccessMessage(final String content){
        getMessages().add(Message.createSuccessMessage(content));
    }

    public void addSuccesMessage(final String s) {
        getMessages().add(Message.createSuccessMessage(s));
    }

}
