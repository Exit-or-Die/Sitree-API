package com.eod.sitree.comment.ui.dto.response;

import com.eod.sitree.comment.domain.model.CommentType;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponseDto {


    private Long commentId;

    private CommentType commentType;

    private Long targetId;

    private String contents;

    private CommentMemberDto createMember;

    private Long parentCommentId;

    private List<CommentResponseDto> childComments;

    private Boolean isChildComment;

    private Boolean isDeleted;

    @Getter
    public static class CommentMemberDto {

        private Long memberId;

        private String nickname;

        private String profileImgUrl;

        private boolean isProjectOwner;

        private boolean isProjectMember;

        public CommentMemberDto(Long memberId, String nickname, String profileImgUrl,
            boolean isProjectOwner, boolean isProjectMember) {

            this.memberId = memberId;
            this.nickname = nickname;
            this.profileImgUrl = profileImgUrl;
            this.isProjectOwner = isProjectOwner;
            this.isProjectMember = isProjectMember;
        }
    }

    public CommentResponseDto(Long commentId, CommentType commentType, Long targetId,
        String contents, Long memberId, String nickname, String profileImgUrl,
        boolean isProjectOwner, boolean isProjectMember, Long parentCommentId,
        Boolean isChildComment, Boolean isDeleted) {

        this.commentId = commentId;
        this.commentType = commentType;
        this.targetId = targetId;
        this.contents = contents;
        this.createMember = new CommentMemberDto(memberId, nickname, profileImgUrl, isProjectOwner, isProjectMember);
        this.parentCommentId = parentCommentId;
        this.childComments = new ArrayList<>();
        this.isChildComment = isChildComment;
        this.isDeleted = isDeleted;
    }

    public void addChildComment(List<CommentResponseDto> childComment) {

        this.childComments = childComment;
    }
}

