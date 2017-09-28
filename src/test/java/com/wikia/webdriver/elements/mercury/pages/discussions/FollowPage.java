package com.wikia.webdriver.elements.mercury.pages.discussions;

import com.wikia.webdriver.elements.mercury.components.discussions.common.NoFollowedPostsMessage;
import com.wikia.webdriver.elements.mercury.components.discussions.common.Post;
import com.wikia.webdriver.elements.mercury.components.discussions.common.SignInToFollowModalDialog;
import lombok.Getter;

public class FollowPage extends PageWithPosts {

  private static final String PATH = "/d/follow";

  @Getter(lazy = true)
  private final NoFollowedPostsMessage noFollowedPostsMessage = new NoFollowedPostsMessage();

  @Getter(lazy = true)
  private final Post post = new Post();

  @Override
  public FollowPage open() {
    final FollowPage page = new FollowPage();
    page.getUrl(page.urlBuilder.getUrlForWiki() + PATH);
    return page;
  }

  @Override
  public SignInToFollowModalDialog getSignInToFollowModalDialog() {
    throw new UnsupportedOperationException("FollowPage not reachable for unauthorized users");
  }
}
